package com.quark.salesorder.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
public class HelloController {
    @CrossOrigin
    @GetMapping(value="/")
    public String getMethodName() {
        return "Olá galera do Java Spring Boot!";
    }
    
}
