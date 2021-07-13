package com.quark.salesorder.ServiceGRPC;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;
import java.util.stream.IntStream;

import com.quark.salesorder.ServiceGRPC.ProductServiceGRPC;
//import com.quark.salesorder.ProductServiceProtoGrpc.ProductRequest;
//import com.grpcserver.grpcserver.GrpcserverServiceGrpc;
import com.quark.salesorder.dtos.ProductDto;

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


    public static SalesProductApi.UpdateAmountResponse updateAmount(List<ProductDto> dtos) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5000).usePlaintext().build();
        SalesProductApi.ProductServiceProtoGrpc.ProductServiceProtoBlockingStub stub = SalesProductApi.ProductServiceProtoGrpc.newBlockingStub(channel);

        
        SalesProductApi.ItemUpdateAmount.Builder products = SalesProductApi.ItemUpdateAmount.newBuilder();


        //IntStream.range(0, 2).forEach(
          // iteration -> {
                final SalesProductApi.UpdateAmountRequest product1 = SalesProductApi.UpdateAmountRequest.newBuilder()
                .setId(1)
                .setAmount("1")
                .build();
                products.addItems(product1);
                final SalesProductApi.UpdateAmountRequest product2 = SalesProductApi.UpdateAmountRequest.newBuilder()
                .setId(2)
                .setAmount("1")
                .build();
                products.addItems(product2);
        //});

        SalesProductApi.ItemUpdateAmount dto = products.build();
        SalesProductApi.UpdateAmountResponse response = stub.updateAmount(dto);

        //SalesProductApi.UpdateAmountResponse response = null; 

        channel.shutdown();
        return response;
    }



}
