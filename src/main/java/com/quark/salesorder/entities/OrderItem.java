package com.quark.salesorder.entities;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private String description;
    private Integer quantity;
    private Double price;
    private Long productId;

    public OrderItem(){
    }

    public OrderItem(String description, Integer quantity, Double price, Long productId) {
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
    }

        public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    
    
}
