package com.example.demo.controllers;

import com.example.demo.domain.User;
import com.example.demo.domain.UserDto;
import com.example.demo.services.EmailService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
public class UserController {

    private UserService userService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/register")
    public ModelAndView registerUserForm(ModelAndView model, UserDto userDto){
        model.addObject("user", userDto);
        model.setViewName("registration");
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, @ModelAttribute("user") @Valid UserDto userDto, BindingResult result,
                                     HttpServletRequest request, Errors errors) throws Exception {

        if(!userDto.getPassword().equals(userDto.getMatchingPassword())){
            modelAndView.addObject("alreadyRegisteredMessage", "Passwords doesn't match");
            modelAndView.setViewName("registration");
            result.reject("email");
            return modelAndView;
        }

        User emailExists = userService.findByUsernameOrEmail(userDto.getEmail());
        User usernameExists = userService.findByUsernameOrEmail(userDto.getUsername());

        if(emailExists != null || usernameExists != null){
            modelAndView.addObject("alreadyRegisteredMessage", "There is an account with that username or email address");
            modelAndView.setViewName("registration");
            result.reject("email");
            return modelAndView;
        }

        if(result.hasErrors()){
            modelAndView.setViewName("registration");
            result.reject("email");
            return modelAndView;
        }

        else{
        userService.registerNewUserAccount(userDto, result);
        modelAndView.setViewName("successRegister");
        return modelAndView;
        }
    }

    @RequestMapping(value = "/register/confirm", method = RequestMethod.GET)
    public ModelAndView activateAccount(ModelAndView modelAndView,
                                        @RequestParam("token") String token){
        User user = userService.findByConfirmationToken(token);

        user.setEnabled(true);

        userService.userSave(user);

        modelAndView.setViewName("activationSuccessful");


        return modelAndView;
    }


    private User createUserAccount(UserDto userDto, BindingResult result){
        User user = null;
        try{
            user = userService.registerNewUserAccount(userDto, result);
        }  catch (Exception e) {
            return null;
        }
        return user;
    }



}
