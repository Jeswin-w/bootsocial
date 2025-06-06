package com.saharaj.bootsocial.userprofile.controller;

import com.saharaj.bootsocial.userprofile.entity.AppUser;
import com.saharaj.bootsocial.userprofile.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthController {

    private UserService userService;
    AuthController (UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/signup")
    public String signupUser(@RequestBody AppUser user) {
        AppUser appUser = userService.addUser(user);
        return appUser != null? "success": "failure";
    }

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }
}
