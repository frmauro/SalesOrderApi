package com.quark.salesorder.ServiceGRPC;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import SalesOrderApi.Empty;
import SalesOrderApi.ItemResponse;
import SalesOrderApi.OrderReply;
import SalesOrderApi.OrderRequest;
import SalesOrderApi.OrderResponse;
import SalesOrderApi.OrderServiceProtoGrpc.OrderServiceProtoImplBase;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderServiceGRPC extends SalesOrderApi.OrderServiceProtoGrpc.OrderServiceProtoImplBase  {
    
     public void SendOrder(OrderRequest request, StreamObserver<OrderReply> responseObserver) 
     {
        Integer id = 2;
        OrderReply reply = OrderReply.newBuilder().setId(id).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
     }

     
     @Override
     public void getOrders(Empty request, StreamObserver<ItemResponse> responseObserver) 
     {
         // TODO Auto-generated method stub
         //super.getOrders(request, responseObserver);

         var ordersResponse = new ArrayList<SalesOrderApi.OrderResponse>();
         ordersResponse.add(OrderResponse.newBuilder().setId(2).build());

         ItemResponse reply = ItemResponse.newBuilder().addAllItems(ordersResponse).build();
        
         responseObserver.onNext(reply);
         responseObserver.onCompleted();
     }
   

}
