package com.quark.salesorder.controllers;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.quark.salesorder.dtos.OrderDto;
import com.quark.salesorder.dtos.OrderItemDto;
import com.quark.salesorder.entities.Order;
import com.quark.salesorder.entities.OrderItem;
import com.quark.salesorder.services.OrdemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> orders = service.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Integer id){
        var order = service.findById(id);
		return ResponseEntity.ok().body(order);
    }
    
    @CrossOrigin
    @PostMapping
	public ResponseEntity<OrderDto> insert(@RequestBody OrderDto dto){
        var entity = convertOrderDtoToOrder(dto);
        service.insert(entity);
        var orderItems = getOrderItems(dto, entity);
        service.insertOrderItemAll(orderItems);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        dto = convertOrderToOrderDto(entity, orderItems);
		return ResponseEntity.created(uri).body(dto);
    }
    
    @CrossOrigin
    @PutMapping(value = "/{id}")
	public ResponseEntity<OrderDto> update(@PathVariable Integer id, @RequestBody OrderDto dto){
        var order = service.findById(id);
        order.setOrderStatus(dto.getOrderStatus());
        service.update(order);
		return ResponseEntity.ok().body(dto);
    }
    
    //private methods *************************
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

    private OrderDto convertOrderToOrderDto(Order entity, List<OrderItem> orderItems){
        var dto = new OrderDto(entity.getId(), entity.getDescription(), entity.getMoment().toString(), entity.getOrderStatus(), entity.getUserId());
        List<OrderItemDto> orderItensDto = new ArrayList<>();
        for (var i : orderItems){
            OrderItemDto orderItemDto = new OrderItemDto(i.getDescription(), i.getQuantity(), i.getPrice(), i.getProductId());
            orderItemDto.setId(entity.getId());
            orderItensDto.add(orderItemDto);
        }
        dto.getItems().addAll(orderItensDto);
        return dto;
    }



}
