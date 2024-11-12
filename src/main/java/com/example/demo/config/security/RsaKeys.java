package com.example.demo.config.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "rsa")
public class RsaKeys {
    
    private RSAPrivateKey privateKey;
    private RSAPublicKey publicKey;

}
