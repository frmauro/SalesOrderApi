package com.quark.salesorder.ServiceGRPC;

import org.springframework.stereotype.Service;

import SalesOrderApi.Empty;
import SalesOrderApi.ItemResponse;
import SalesOrderApi.OrderReply;
import SalesOrderApi.OrderRequest;
import SalesOrderApi.OrderServiceProtoGrpc.OrderServiceProtoImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderServiceGRPC extends SalesOrderApi.OrderServiceProtoGrpc.OrderServiceProtoImplBase  {
    
     public void SendOrder(OrderRequest request, StreamObserver<OrderReply> responseObserver) {
        Integer id = 2;
        OrderReply reply = OrderReply.newBuilder().setId(id).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
     }

     public void GetOrders(Empty request, StreamObserver<ItemResponse> responseObserver) {
        ItemResponse reply = ItemResponse.newBuilder().build();
        
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
     }

}
