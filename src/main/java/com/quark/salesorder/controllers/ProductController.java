package com.quark.salesorder.controllers;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.protobuf.util.JsonFormat;
import com.quark.salesorder.ServiceGRPC.ProductServiceGRPC;
import com.quark.salesorder.dtos.ProductDto;
import com.quark.salesorder.helpers.JSONUtils;
import com.quark.salesorder.services.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    // ****** Http method *****
    // @CrossOrigin
    // @GetMapping
    // public ResponseEntity<String> findAll() throws JsonParseException, JsonMappingException, InterruptedException,
    //         ExecutionException, IOException, KeyManagementException, NoSuchAlgorithmException {
    //     String productsJson = ProductService.getAllProducts();
    //     return ResponseEntity.ok().body(productsJson);
    // }


    // ****** grpc method *****
    @CrossOrigin
    @GetMapping
    public ResponseEntity<String> findAll() throws IOException  {
        SalesProductApi.ItemResponse productsJson = ProductServiceGRPC.getAllProducts();
        String json = JsonFormat.printer()
        .preservingProtoFieldNames()
        .includingDefaultValueFields()
        .print(productsJson);
        return ResponseEntity.ok().body(json);
    }

    // ****** Http method *****
    // @CrossOrigin
    // @GetMapping(value = "/{id}")
    // public ResponseEntity<String> findById(@PathVariable Integer id)
    //         throws JsonParseException, JsonMappingException, InterruptedException, 
    //                 ExecutionException, IOException,
    //                 KeyManagementException, NoSuchAlgorithmException {
    //     String productJson = ProductService.getProductById(id);
    //     return ResponseEntity.ok().body(productJson);
    // }


    // ****** grpc method *****
    @CrossOrigin
    @GetMapping(value = "/{id}")
    public ResponseEntity<String> findById(@PathVariable Integer id) throws IOException {
        SalesProductApi.ProductResponse productJson = ProductServiceGRPC.getById(id);
        String json = JsonFormat.printer()
        .preservingProtoFieldNames()
        .includingDefaultValueFields()
        .print(productJson);
        return ResponseEntity.ok().body(json);
    }


    // ****** Http method *****
    // @CrossOrigin
    // @PostMapping
    // public ResponseEntity<String> updateAmount(@RequestBody List<ProductDto> dtos)
    //         throws InterruptedException, ExecutionException, IOException, 
    //         KeyManagementException, NoSuchAlgorithmException 
    // {
    //     String dtosJson = "";
    //     dtosJson = JSONUtils.covertFromObjectToJson(dtos);
    //     var result = ProductService.updateAmount(dtosJson);
    //     return ResponseEntity.ok().body(result);
    // }


    // ****** grpc method *****
    @CrossOrigin
    @PostMapping
    public ResponseEntity<String> updateAmount(@RequestBody List<ProductDto> dtos)  
        throws InterruptedException, ExecutionException, IOException 
    {
        String dtosJson = "";
        //dtosJson = JSONUtils.covertFromObjectToJson(dtos);
        var result = ProductServiceGRPC.updateAmount(dtos);
        return ResponseEntity.ok().body(dtosJson);
    }



    @CrossOrigin
    @PostMapping(value = "/testPost")
    public ResponseEntity<String> testPost(@RequestBody String dto)
            throws InterruptedException, ExecutionException, IOException, 
            KeyManagementException, NoSuchAlgorithmException, TimeoutException 
    {
        var result = ProductService.testPost(dto);
        return ResponseEntity.ok().body(result);
    }


}
