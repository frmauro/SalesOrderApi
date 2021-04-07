package com.quark.salesorder.controllers;

import com.quark.salesorder.dtos.UserDto;

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
    public ResponseEntity<String> updateAmount(@RequestBody UserDto dto)
    {
        String result = dto.getEmail();
        //dtosJson = JSONUtils.covertFromObjectToJson(dtos);
        //var result = ProductService.updateAmount(dtosJson);
        return ResponseEntity.ok().body(result);
    }
    
}
