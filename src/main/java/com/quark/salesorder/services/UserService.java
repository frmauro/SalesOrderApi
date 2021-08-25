package com.quark.salesorder.services;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.JsonMappingException;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class UserService {


      // use link to service kubernetes
      private static final String SERVICEURL = "http://apiuserhttp:8088/user/";

    // use link to docker container with compose
     //private static final String SERVICEURL = "http://user-api:8088/user/";

     // use from container to docker container without compose
      //private static final String SERVICEURL = "http://salesusernode:8088/user/";

     // use from vscode to docker container without compose
      //private static final String SERVICEURL = "http://localhost:8088/user/";
    


     //sending request to amount update the products.
    public static String getByEmailAndPassword(String dtoJson) 
      throws InterruptedException, ExecutionException, JsonMappingException, IOException,
             KeyManagementException, NoSuchAlgorithmException
    {
            String requestJson = dtoJson;//"[{\"_id\":\"\",\"name\":\"\",\"email\":" + email + ",\"password\":" + password + ",\"status\":\"\",\"token\":\"\",\"userType\":\"\" }]";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<String>(requestJson,headers);
            String result = restTemplate.postForObject(SERVICEURL, entity, String.class);
            return result;
    }

}