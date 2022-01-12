package com.quark.salesorder.helpers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.quark.salesorder.dtos.OrderDto;
import com.quark.salesorder.dtos.OrderItemDto;
import com.quark.salesorder.entities.Order;
import com.quark.salesorder.entities.OrderItem;

public class EntityHelper {
    public Order convertOrderDtoToOrder(OrderDto dto){
        var entity = new Order(dto.getDescription(), Instant.parse("2020-07-20T19:53:07Z"), dto.getOrderStatus(), dto.getUserId());
        var items = dto.getItems();

        for (var i : items){
            OrderItem orderItem = new OrderItem(i.getDescription(), i.getQuantity(), i.getPrice(), i.getProductId());
            orderItem.getOrders().add(entity);
            entity.getItems().add(orderItem);
        }
        return entity;
    }

    public OrderDto convertOrderToOrderDto(Order entity){
        var dto = new OrderDto(entity.getId(), entity.getDescription(), entity.getMoment().toString(), entity.getOrderStatus(), entity.getUserId());
        List<OrderItemDto> orderItensDto = new ArrayList<>();
        var items = entity.getItems();

        for (var i : items){
            OrderItemDto orderItemDto = new OrderItemDto(i.getDescription(), i.getQuantity(), i.getPrice(), i.getProductId());
            orderItemDto.setId(entity.getId());
            orderItensDto.add(orderItemDto);
        }
        dto.getItems().addAll(orderItensDto);
        return dto;
    }
}
