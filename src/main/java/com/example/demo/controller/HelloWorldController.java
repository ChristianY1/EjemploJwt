package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.util.JwtUtil;

@RestController
public class HelloWorldController {
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/generateToken")
    public String generateToken(@RequestParam String username) {
        return jwtUtil.generateToken(username);
    }

    @GetMapping("/hello")
    public String helloWorld(@RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token);
        if (jwtUtil.validateToken(token, username)) {
            return "Hola Mundo";
        } else {
            return "Token inválido";
        }
    }
}
