package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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