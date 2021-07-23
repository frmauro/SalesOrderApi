package com.quark.salesorder.ServiceGRPC;

import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Service
public class UserServiceGRPC {
     //Local PORT 
    private static final Integer PORT = 50051;

    //container PORT 
    //private static final Integer PORT = 9090;
    
    // use from local to docker container without compose
    //private static final String SERVICEURL = "localhost";
    //private static final String SERVICEURL = "172.17.0.6";
    // use from container to docker container without compose
    private static final String SERVICEURL = "salesusernode";


      public static SalesUserApi.User findByEmailAndPassword(String email, String password) {
          ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVICEURL, PORT).usePlaintext().build();
          SalesUserApi.UserServiceGrpc.UserServiceBlockingStub stub = SalesUserApi.UserServiceGrpc.newBlockingStub(channel);
          SalesUserApi.User response = 
            stub.findByEmailAndPassword(SalesUserApi.UserEmailPassword.newBuilder()
            .setEmail(email)
            .setPassword(password)
            .build());
          channel.shutdown();
          return response;
      }
}