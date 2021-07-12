package com.quark.salesorder.ServiceGRPC;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import com.quark.salesorder.ServiceGRPC.ProductServiceGRPC;
//import com.quark.salesorder.ProductServiceProtoGrpc.ProductRequest;
//import com.grpcserver.grpcserver.GrpcserverServiceGrpc;

@Service
public class ProductServiceGRPC {
    
    public static String getAllProducts() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000).usePlaintext().build();
        SalesProductApi.ProductServiceProtoGrpc.ProductServiceProtoBlockingStub stub = SalesProductApi.ProductServiceProtoGrpc.newBlockingStub(channel);
        SalesProductApi.ItemResponse response = stub.getProducts(SalesProductApi.Empty.newBuilder().build());//.newBuilder().setName("Francisco").build());
        channel.shutdown();
        return "OK";
    }


}
