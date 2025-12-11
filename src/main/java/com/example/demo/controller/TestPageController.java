package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestPageController {

    @GetMapping("/")
    public String homePage() {
        return "<!DOCTYPE html>" +
               "<html>" +
               "<head><title>API Test Page</title></head>" +
               "<body>" +
               "<h1>🎉 Your Spring Boot API is Live!</h1>" +
               "<p>Welcome to your Railway-hosted API.</p>" +
               "<p>Try accessing <code>/test/db</code> to test your database connection.</p>" +
               "</body>" +
               "</html>";
    }
}
