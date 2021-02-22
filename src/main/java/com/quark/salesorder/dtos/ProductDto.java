package com.quark.salesorder.dtos;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String productId; 
    private String description;
    private String amount;
    private String price;
    private String status;


    public ProductDto(){}

    public ProductDto(String productId, String description, String amount, String price, String status) {
        this.productId = productId;
        this.description = description;
        this.amount = amount;
        this.price = price;
        this.status = status;
    }
    

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
