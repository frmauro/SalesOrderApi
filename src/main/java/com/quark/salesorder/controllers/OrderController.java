package com.quark.salesorder.controllers;

import java.net.URI;
import java.util.List;

import com.quark.salesorder.dtos.OrderDto;
import com.quark.salesorder.entities.Order;
import com.quark.salesorder.services.OrdemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
	private OrdemService service;
    
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> orders = service.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Integer id){
        var order = service.findById(id);
		return ResponseEntity.ok().body(order);
    }
    

    @PostMapping
	public ResponseEntity<OrderDto> insert(@RequestBody OrderDto dto){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody OrderDto dto){
		return ResponseEntity.ok().body(dto);
	}


}
