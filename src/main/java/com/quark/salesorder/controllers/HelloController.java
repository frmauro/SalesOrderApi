package com.quark.salesorder.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HelloController {
    @GetMapping(value="/")
    public String getMethodName() {
        return "Olá galera do Java Spring Boot!";
    }
    
}
