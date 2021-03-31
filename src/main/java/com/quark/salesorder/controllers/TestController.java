package com.quark.salesorder.controllers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.quark.salesorder.services.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/tests")
public class TestController {

    @CrossOrigin
    @GetMapping
    public ResponseEntity<String> getMethodName() throws JsonParseException, JsonMappingException, KeyManagementException, NoSuchAlgorithmException, InterruptedException, ExecutionException, IOException {
        String result = ProductService.getTest();
        return ResponseEntity.ok().body(result);
        //return "Test method via get !!!";
    }

}