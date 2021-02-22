package com.quark.salesorder.services;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductService(){
    }

    private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();
    private static final String serviceURL = "http://localhost:8087/Product/";
    
}
