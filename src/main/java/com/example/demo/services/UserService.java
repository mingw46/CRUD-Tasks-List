package com.example.demo.services;

import com.example.demo.domain.User;
import com.example.demo.domain.UserDto;
import org.springframework.validation.BindingResult;

public interface UserService {

    User findByUsernameOrEmail(String usernameOrEmail);

    User registerNewUserAccount(UserDto userDto, BindingResult result) throws Exception;

    User findByConfirmationToken(String confirmationToken);

    User userSave(User user);

}
