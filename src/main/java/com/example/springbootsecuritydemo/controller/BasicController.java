package com.example.springbootsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BasicController {

    @GetMapping("/public")
    public String publicMethod(){
        return " This is a public api";
    }
    @GetMapping("/user")
    public String user(){
        return "This is a user api";
    }
    @GetMapping("/admin")
    public String amdin(){
        return " this is a admin api";
    }
}
