package com.quark.salesorder.controllers;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.quark.salesorder.dtos.OrderDto;
import com.quark.salesorder.entities.Order;
import com.quark.salesorder.entities.OrderItem;
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
        var entity = convertOrderDtoToOrder(dto);
        service.insert(entity);
        var orderItems = getOrderItems(dto, entity);
        service.insertOrderItemAll(orderItems);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        dto.setId(entity.getId());
		return ResponseEntity.created(uri).body(dto);
    }
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<OrderDto> update(@PathVariable Long id, @RequestBody OrderDto dto){
		return ResponseEntity.ok().body(dto);
    }
    

    private Order convertOrderDtoToOrder(OrderDto dto){
        return new Order(null, dto.getDescription(), Instant.parse("2020-07-20T19:53:07Z"), dto.getOrderStatus(), dto.getUserId());
    }

    private List<OrderItem> getOrderItems(OrderDto dto, Order entity){
        List<OrderItem> orderItens = new ArrayList<>();
        for (var i : dto.getItems()){
            OrderItem orderItem = new OrderItem(entity, i.getDescription(), i.getQuantity(), i.getPrice(), i.getProductId());
            orderItens.add(orderItem);
        }
        return orderItens;
    }


}
