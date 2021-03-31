package com.quark.salesorder.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin
@RestController
public class HelloController {
    @CrossOrigin
    @GetMapping(value="/")
    public String getMethodName() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String localDateTime = LocalDateTime.now().format(formatter);
        return "Olá galera do Java Spring Boot! - Data01 - " + localDateTime;
    }
    
}
