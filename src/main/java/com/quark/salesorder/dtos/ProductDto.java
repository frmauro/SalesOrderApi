package com.quark.salesorder.dtos;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id; 
    private String description;
    private Integer amount;
    private String status;
    private String price;


     public ProductDto(){}

     public ProductDto(Long id, String description, Integer amount, String price, String status) {
         super();
         this.id = id;
         this.description = description;
         this.amount = amount;
         this.price = price;
         this.status = status;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    
}
