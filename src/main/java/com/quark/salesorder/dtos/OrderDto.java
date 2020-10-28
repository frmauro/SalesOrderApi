package com.quark.salesorder.dtos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class OrderDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String description;
    private String moment;
    private Integer orderStatus;
    private Integer userId;
    private Set<OrderItemDto> items = new HashSet<>();

    public OrderDto(){
    }

    public OrderDto(Integer id, String description, String moment, Integer orderStatus, Integer userId) {
        super();
        this.id = id;
        this.description = description;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMoment() {
        return moment;
    }

    public void setMoment(String moment) {
        this.moment = moment;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Set<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(Set<OrderItemDto> items) {
        this.items = items;
    }
    
}
