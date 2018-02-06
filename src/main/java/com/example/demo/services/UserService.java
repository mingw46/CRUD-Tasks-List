package com.example.demo.services;

import com.example.demo.domain.User;

public interface UserService {

    User findByUsernameOrEmail(String usernameOrEmail);

}
