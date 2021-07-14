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

    //Local PORT 
    //private static final Integer PORT = 5000;

    //container PORT 
    private static final Integer PORT = 9087;
    
    public static SalesProductApi.ItemResponse getAllProducts() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT).usePlaintext().build();
        SalesProductApi.ProductServiceProtoGrpc.ProductServiceProtoBlockingStub stub = SalesProductApi.ProductServiceProtoGrpc.newBlockingStub(channel);
        SalesProductApi.ItemResponse response = stub.getProducts(SalesProductApi.Empty.newBuilder().build());//.newBuilder().setName("Francisco").build());
        channel.shutdown();
        return response;
    }

    public static SalesProductApi.ProductResponse getById(Integer id) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT).usePlaintext().build();
        SalesProductApi.ProductServiceProtoGrpc.ProductServiceProtoBlockingStub stub = SalesProductApi.ProductServiceProtoGrpc.newBlockingStub(channel);
        SalesProductApi.ProductResponse response = stub.getProduct(SalesProductApi.ProductId.newBuilder().setId(id).build());
        channel.shutdown();
        return response;
    }


    public static SalesProductApi.UpdateAmountResponse updateAmount(List<ProductDto> dtos) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT).usePlaintext().build();
        SalesProductApi.ProductServiceProtoGrpc.ProductServiceProtoBlockingStub stub = SalesProductApi.ProductServiceProtoGrpc.newBlockingStub(channel);

        
        SalesProductApi.ItemUpdateAmount.Builder products = SalesProductApi.ItemUpdateAmount.newBuilder();


        for (ProductDto productDto : dtos) {
            SalesProductApi.UpdateAmountRequest product = SalesProductApi.UpdateAmountRequest.newBuilder()
            .setId(org.springframework.util.NumberUtils.convertNumberToTargetClass(productDto.getId(), Integer.class))
            .setAmount(productDto.getAmount().toString())
            .build();
            products.addItems(product);
        }

        SalesProductApi.ItemUpdateAmount dto = products.build();
        SalesProductApi.UpdateAmountResponse response = stub.updateAmount(dto);

        //SalesProductApi.UpdateAmountResponse response = null; 

        channel.shutdown();
        return response;
    }



}
