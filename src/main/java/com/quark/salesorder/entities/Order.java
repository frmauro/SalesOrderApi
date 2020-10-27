package com.quark.salesorder.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


//   ***** OrderStatus - WAITING_PAYMENT(1),PAID(2),SHIPPED(3),DELIVERED(4),CANCELED(5) *******

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private Instant moment;
    private Integer orderStatus;
    
    private Integer userId;
    
    private Set<OrderItem> items = new HashSet<>();

    public Order(){
    }

    public Order(Long id, String description, Instant moment, Integer orderStatus, Integer userId) {
        super();
        this.id = id;
        this.description = description;
        this.moment = moment;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
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

    public Set<OrderItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderItem> items) {
        this.items = items;
    }
   
    
}
