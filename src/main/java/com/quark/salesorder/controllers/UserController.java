package com.quark.salesorder.controllers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.quark.salesorder.dtos.UserDto;
import com.quark.salesorder.helpers.JSONUtils;
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
    public ResponseEntity<String> returnByEmailAndPassword(@RequestBody UserDto dto) 
        throws KeyManagementException, NoSuchAlgorithmException, InterruptedException, ExecutionException, IOException
    {
        String dtoJson = "";
        dtoJson = JSONUtils.covertFromObjectToJson(dto);
        var result = UserService.getByEmailAndPassword(dtoJson);
        return ResponseEntity.ok().body(result);
    }
    
}
