package com.quark.salesorder.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.stereotype.Service;

import com.quark.salesorder.dtos.ProductDto;
import com.quark.salesorder.helpers.JSONUtils;

@Service
public class ProductService {

    private ProductService(){
    }

    private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();
    private static final String SERVICEURL = "http://localhost:8087/Product/";
    private static final String SERVICEURL2 = "http://localhost:8087/UpdateAmount/";

    //sending request to retrieve all the products available.
    public static String getAllProducts() 
        throws InterruptedException, ExecutionException, JsonParseException, JsonMappingException, 
        IOException
    {

        HttpRequest req = HttpRequest.newBuilder(URI.create(SERVICEURL)).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
        //response.thenAcceptAsync(res -> res);

        //List<ProductDto> products = JSONUtils.convertFromJsonToList(response.get().body(), new TypeReference<List<ProductDto>>() {});
        String responseJson = response.get().body();

        return responseJson;
    }

    //sending request to retrieve the products by id available.
    public static String getProductById(Integer id) 
      throws InterruptedException, ExecutionException, JsonParseException, JsonMappingException, 
      IOException
    {
        HttpRequest req = HttpRequest.newBuilder(URI.create(SERVICEURL+id)).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
        String responseJson = response.get().body();
        return responseJson;
    }


    //sending request to amount update the products.
    public static String updateAmount(String productsDto) 
      throws InterruptedException, ExecutionException, JsonParseException, JsonMappingException, IOException
    {

        //String testePost = "[{'amount':1,'description':'','price':'0','status':'Active','id':1},{'amount':1,'description':'','price':'0','status':'Active','id':2}]";

        HttpRequest request = HttpRequest.newBuilder(URI.create(SERVICEURL2))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(productsDto))
                    .build();

                    CompletableFuture<HttpResponse<String>> response = 
                            client.sendAsync(request,HttpResponse.BodyHandlers.ofString());
                            
                    String result = response.get().body();

        return result;
    }

}
