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
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 4999).usePlaintext().build();
        //com.quark.salesorder.ProductServiceProto.ProductServiceProtoBlockingStub. stub = com.quark.salesorder.ProductServiceProto.newBlockingStub(channel);
        //HelloReply helloReply = stub.sayHello(HelloRequest.newBuilder().setName("Francisco").build());
        channel.shutdown();
        return "OK";
    }


}
