package com.quark.salesorder.dtos;

import java.io.Serializable;

public class OrderItemDto implements Serializable{
    private static final long serialVersionUID = 1L;
    
    
	private Integer id;
    private String description;
    private Integer quantity;
    private Double price;
    private Integer productId;

    public OrderItemDto(){
    }

    public OrderItemDto(String description, Integer quantity, Double price, Integer productId) {
        super();
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    
}
