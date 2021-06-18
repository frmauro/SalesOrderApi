package com.quark.salesorder.ServiceGRPC;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.quark.salesorder.ServiceGRPC.ProductService;
//import com.quark.salesorder.ProductServiceProtoGrpc.ProductRequest;
//import com.grpcserver.grpcserver.GrpcserverServiceGrpc;

public class ProductService {
    
    public static String getAllProducts() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8030).usePlaintext().build();
        //ProductService.ProductServiceBlockingStub stub = ProductService.newBlockingStub(channel);
        //HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder().setName("Francisco").build());
        channel.shutdown();
        return "OK";
    }


}
