package com.example.demo.api;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/public")
    public String publicApi() {
        return "Public API - All";
    }

    @GetMapping("/protected")
    public String protectedApi(@AuthenticationPrincipal Jwt jwt) {
        return "Protected API - Users";
    }

    @GetMapping("/private")
    public String privateApi(@AuthenticationPrincipal Jwt jwt) {
        return "Private API - Admins";
    }
    
}
