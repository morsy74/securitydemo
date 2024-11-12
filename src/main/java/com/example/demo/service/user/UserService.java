package com.example.demo.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.security.TokenService;
import com.example.demo.database.entity.User;
import com.example.demo.database.repository.UserRepository;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        var username = loginRequest.getUsername();
        var password = loginRequest.getPassword();
        var authToken = new UsernamePasswordAuthenticationToken(username, password);
        var authentication = authenticationManager.authenticate(authToken);
        var token = tokenService.generateToken(authentication);
        return new LoginResponse(token);
    }

    public SignupResponse signup(SignupRequest signupRequest) {
        String username = signupRequest.getUsername();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            throw new RuntimeException("User Already Exists");
        } else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
            user.setEnabled(true);
            user.setAccountNonExpired(true);
            user.setAccountNonLocked(true);
            user.setCredentialsNonExpired(true);
            user.setRoles(List.of("USER"));
            user = userRepository.save(user);
            SignupResponse response = new SignupResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            return response;
        }
    }
    
}
