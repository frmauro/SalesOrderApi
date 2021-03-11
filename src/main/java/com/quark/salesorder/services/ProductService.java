package com.quark.salesorder.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import net.minidev.json.JSONObject;

import com.quark.salesorder.dtos.ProductDto;
import com.quark.salesorder.helpers.JSONUtils;

@Service
public class ProductService {

    private static final Map<String, String> MY_MAP = new HashMap<String, String>();

    private ProductService() {
    }

    //private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();

     private static final String SERVICEURL = "http://product-api/Product/";
     private static final String SERVICEURL2 = "http://product-api/UpdateAmount/";
     private static final String SERVICEURL3 = "http://product-api/Product/TestPost/";
     private static final String SERVICEURL4 = "http://product-api/Test/";

      //private static final String SERVICEURL = "http://localhost:8087/Product/";
      //private static final String SERVICEURL2 = "http://localhost:8087/UpdateAmount/";

    //private static final String SERVICEURL = "https://localhost:5001/Product/";
    //private static final String SERVICEURL2 = "https://localhost:5001/UpdateAmount/";

    // sending request to retrieve all the products available.
    public static String getAllProducts() throws InterruptedException, ExecutionException, JsonParseException,
            JsonMappingException, IOException, KeyManagementException, NoSuchAlgorithmException
    {
        HttpClient client = getClient();
        HttpRequest req = HttpRequest.newBuilder(URI.create(SERVICEURL)).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
        return response.get().body();
    }


    //sending request to retrieve the products by id available.
    public static String getProductById(Integer id) 
      throws InterruptedException, ExecutionException, JsonParseException, JsonMappingException, 
      IOException, KeyManagementException, NoSuchAlgorithmException
    {
        HttpRequest req = HttpRequest.newBuilder(URI.create(SERVICEURL+id)).GET().build();
        var client = getClient();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
        String responseJson = response.get().body();
        return responseJson;
    }


    //sending request to amount update the products.
    public static Integer updateAmount(String productsDto) 
      throws InterruptedException, ExecutionException, JsonParseException, 
             JsonMappingException, IOException,
             KeyManagementException, NoSuchAlgorithmException
    {

        HttpRequest request = HttpRequest.newBuilder(URI.create(SERVICEURL2))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(productsDto))
                    .build();

                    //HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();

                    var client = getClient();
                     
                    CompletableFuture<HttpResponse<String>> response = 
                        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
                    return response.get().statusCode();//response.get().body();
    }


     //sending request to amount update the products.
     public static String testPost(String teste) 
     throws InterruptedException, ExecutionException, JsonParseException, 
            JsonMappingException, IOException,
            KeyManagementException, NoSuchAlgorithmException, TimeoutException
   {
            
            Map<String, String> map = new HashMap<String, String>();
            map.put("Name", "teste");

            ObjectMapper objectMapper = new ObjectMapper();
            String requestBody = objectMapper
                            .writerWithDefaultPrettyPrinter()
                            .writeValueAsString(map);

            HttpRequest request = HttpRequest.newBuilder(URI.create(SERVICEURL3))
            .header("Content-Type","application/json")
            .POST(BodyPublishers.ofString("{\"Name\":\"francisco\"}"))
            .build();

            //var client = HttpClient.newHttpClient();
            var client = getClient();

            //CompletableFuture<String> response = client.sendAsync(request, BodyHandlers.ofString()).thenApply(HttpResponse::body);

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String result = "Version: " + response.version();
            result += "\nURI: " + response.uri();
            result += "\nStatus code: " + response.statusCode();
            result += "\nHeaders: " + response.headers();
            result += "\n Body: " + response.body();
            result += "\n jsonRequestBody: " + requestBody;

            //CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, BodyHandlers.ofString());
              //String responseJson = response.get().body();
            return result;//response.get();
   }


    // sending request to retrieve all the products available.
    public static String getTest() throws InterruptedException, ExecutionException, JsonParseException,
            JsonMappingException, IOException, KeyManagementException, NoSuchAlgorithmException
    {
        HttpClient client = getClient();
        HttpRequest req = HttpRequest.newBuilder(URI.create(SERVICEURL4)).GET().build();
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(req, BodyHandlers.ofString());
        return response.get().body();
    }



    private static HttpClient getClient() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new SecureRandom());
        return HttpClient.newBuilder().sslContext(sslContext).version(Version.HTTP_2).build();
    }


    private static TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
            public void checkClientTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
            }
            public void checkServerTrusted(
                java.security.cert.X509Certificate[] certs, String authType) {
            }
        }
    };


}
