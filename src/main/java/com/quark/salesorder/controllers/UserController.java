package com.quark.salesorder.controllers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.quark.salesorder.dtos.UserDto;
import com.quark.salesorder.services.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {


    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> getByEmailAndPassword(@RequestBody UserDto dto) 
        throws JsonMappingException, KeyManagementException, NoSuchAlgorithmException, 
               InterruptedException, ExecutionException, IOException
    {
        String email = dto.getEmail();
        String password = dto.getPassword();
        var result = UserService.getByEmailAndPassword(email, password);
        return ResponseEntity.ok().body(result);
    }
    
}
