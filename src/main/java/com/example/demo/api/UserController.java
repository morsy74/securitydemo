package com.example.demo.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.user.LoginRequest;
import com.example.demo.service.user.LoginResponse;
import com.example.demo.service.user.SignupRequest;
import com.example.demo.service.user.SignupResponse;
import com.example.demo.service.user.UserService;

@RestController
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("/signup")
    public SignupResponse login(@RequestBody SignupRequest signupRequest) {
        return userService.signup(signupRequest);
    }

}
