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
    
    public static SalesProductApi.ItemResponse getAllProducts() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000).usePlaintext().build();
        SalesProductApi.ProductServiceProtoGrpc.ProductServiceProtoBlockingStub stub = SalesProductApi.ProductServiceProtoGrpc.newBlockingStub(channel);
        SalesProductApi.ItemResponse response = stub.getProducts(SalesProductApi.Empty.newBuilder().build());//.newBuilder().setName("Francisco").build());
        channel.shutdown();
        return response;
    }

    public static SalesProductApi.ProductResponse getById(Integer id) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000).usePlaintext().build();
        SalesProductApi.ProductServiceProtoGrpc.ProductServiceProtoBlockingStub stub = SalesProductApi.ProductServiceProtoGrpc.newBlockingStub(channel);
        SalesProductApi.ProductResponse response = stub.getProduct(SalesProductApi.ProductId.newBuilder().setId(id).build());
        channel.shutdown();
        return response;
    }


}
