package com.quark.salesorder.dtos;

import java.io.Serializable;

public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String email;
    private String password;
    private String token;

    public UserDto(){}

    public UserDto(String id, String email, String password, String token) {
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }



}
