package com.quark.salesorder.controllers;

import java.time.Instant;

import java.util.List;
import java.util.Arrays;

import com.quark.salesorder.entities.Order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){

        var o1 = new Order(null, "Product 001", Instant.parse("2019-07-20T19:53:07Z"), 1, 1);
        var o2 = new Order(null, "Product 002", Instant.parse("2019-06-20T19:53:07Z"), 2, 2);
        var o3 = new Order(null, "Product 003", Instant.parse("2019-08-20T19:53:07Z"), 2, 1);

        List<Order> orders = Arrays.asList(o1, o2, o3);
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable long id){
        var order = new Order(null, "Product 001", Instant.parse("2019-07-20T19:53:07Z"), 1, 1);
		return ResponseEntity.ok().body(order);
	}


}
