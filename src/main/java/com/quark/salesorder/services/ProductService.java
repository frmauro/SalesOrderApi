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
import java.time.Duration;
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

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import net.minidev.json.JSONObject;

import com.quark.salesorder.dtos.ProductDto;
import com.quark.salesorder.helpers.JSONUtils;

@Service
public class ProductService {

    private static final Map<String, String> MY_MAP = new HashMap<String, String>();

    private ProductService() {
    }

    //private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();

    // use link to docker container with compose
    //  private static final String SERVICEURL = "http://product-api/Product/";
    //  private static final String SERVICEURL2 = "http://product-api/UpdateAmount/";
    //  private static final String SERVICEURL3 = "http://product-api/Product/TestPost/";
    //  private static final String SERVICEURL4 = "http://product-api/Test/";

    // use link to docker container to local use
    //  private static final String SERVICEURL = "https://localhost:5001/Product/";
    //  private static final String SERVICEURL2 = "https://localhost:5001/UpdateAmount/";
    //  private static final String SERVICEURL3 = "https://localhost:5001/Product/TestPost/";
    //  private static final String SERVICEURL4 = "https://localhost:5001/Test/";

     // use from container to docker container without compose
    //  private static final String SERVICEURL = "http://salesproductapi/Product/";
    //  private static final String SERVICEURL2 = "http://salesproductapi/UpdateAmount/";
    //  private static final String SERVICEURL3 = "http://salesproductapi/Product/TestPost/";
    //  private static final String SERVICEURL4 = "http://salesproductapi/Test/";

     // use from local to docker container without compose
     private static final String SERVICEURL = "http://localhost:8087/Product/";
     private static final String SERVICEURL2 = "http://localhost:8087/UpdateAmount/";
     private static final String SERVICEURL3 = "http://localhost:8087/Product/TestPost/";
     private static final String SERVICEURL4 = "http://localhost:8087/Test/";

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
    public static String updateAmount(String productsDto) 
      throws InterruptedException, ExecutionException, JsonParseException, 
             JsonMappingException, IOException,
             KeyManagementException, NoSuchAlgorithmException
    {
            // String amount = "1";
            // String id = "1";
            // String requestJson = "[{\"amount\":" + amount + ",\"description\":\"\",\"price\":\"0\",\"status\":\"Active\",\"id\":" + id + "}]";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(productsDto,headers);
            String result = restTemplate.postForObject(SERVICEURL2, entity, String.class);
            return result;
    }


     //sending request to amount update the products.
     public static String testPost(String teste) 
     throws InterruptedException, ExecutionException, JsonParseException, 
            JsonMappingException, IOException,
            KeyManagementException, NoSuchAlgorithmException, TimeoutException
   {
            
            RestTemplate restTemplate = new RestTemplate();
            String requestJson = "{\"Name\":\"Chico\"}";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            String result = restTemplate.postForObject(SERVICEURL4, entity, String.class);
            
            return result;
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

    private static final HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .build();

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
