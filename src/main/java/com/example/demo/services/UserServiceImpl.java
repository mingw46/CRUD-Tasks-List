package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.domain.UserDto;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService){
        this.emailService = emailService;
    }

    @Transactional
    @Override
    public User registerNewUserAccount(UserDto userDto, BindingResult result) throws Exception {
    if(emailExists(userDto.getEmail())){
        throw new Exception("There is an account with that email address" + userDto.getEmail());
    }

    User user = new User();
    user.setUsername(userDto.getUsername());
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    user.setEmail(userDto.getEmail());
    user.setRole("ROLE_USER");
    user.setEnabled(false);

    user.setConfirmationToken(UUID.randomUUID().toString());

        emailService.SendEmail(userDto.getEmail(), "Registration Confirmation",
                "Hello " + user.getUsername() + "\nTo confirm your email, click the  below\n"
                        + "http://localhost:8080/register" + "/confirm?token="  + user.getConfirmationToken());

    return userRepository.save(user);
    }

    private boolean emailExists(String email){
        User user = userRepository.findByEmail(email);
        if(user != null){
            return true;
        }
        return false;
    }



    @Override
    @Transactional(readOnly = true)
    public User findByConfirmationToken(String confirmationToken) {
        User user = null;
        try {
            user = userRepository.findByConfirmationToken(confirmationToken);
        } catch (Exception e){
            throw e;
        }
        return user;
    }

    @Override
    @Transactional
    public User userSave(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsernameOrEmail(String usernameOrEmail) {
        User user = null;
        try {
            user = userRepository.findByUsernameOrEmail(usernameOrEmail);
        } catch (Exception e) {
            throw e;
        }
        return user;
    }




}