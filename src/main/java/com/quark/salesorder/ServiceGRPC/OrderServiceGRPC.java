package com.quark.salesorder.ServiceGRPC;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import SalesOrderApi.Empty;
import SalesOrderApi.ItemOrderResponse;
import SalesOrderApi.ItemResponse;
import SalesOrderApi.ItemsOrderResponse;
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

         var itemsOrder = new ArrayList<ItemOrderResponse>();
         itemsOrder.add(ItemOrderResponse.newBuilder()
         .setId(2)
         .setDescription("Product 002")
         .setPrice("200,0")
         .setProductId(2)
         .setQuantity(300)
         .build()
         );

         var itemsOrderResponse = ItemsOrderResponse.newBuilder().addAllItems(itemsOrder).build();
         
         ordersResponse.add(OrderResponse.newBuilder()
         .setId(2)
         .setDescription("order 002")
         .setMoment("11/01/2022")
         .setStatus("Active")
         .setUserid("611aa80245c2ed2212c3ec3d")
         .setItems(itemsOrderResponse)
         .build());

         ItemResponse reply = ItemResponse.newBuilder().addAllItems(ordersResponse).build();
        
         responseObserver.onNext(reply);
         responseObserver.onCompleted();
     }
   

}
