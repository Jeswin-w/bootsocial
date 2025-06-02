package com.saharaj.bootsocial.userprofile.controller;

import com.saharaj.bootsocial.userprofile.entity.User;
import com.saharaj.bootsocial.userprofile.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignupController {

    private UserService userService;
    SignupController (UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public User signupUser(@RequestBody User user) {
        return userService.addUser(user);
    }
}
