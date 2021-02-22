package com.quark.salesorder.controllers;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.quark.salesorder.dtos.ProductDto;
import com.quark.salesorder.services.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<ProductDto>> findAll()
            throws JsonParseException, JsonMappingException, InterruptedException, ExecutionException, IOException {
        List<ProductDto> products = ProductService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }

}
