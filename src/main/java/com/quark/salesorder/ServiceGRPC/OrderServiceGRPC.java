package com.quark.salesorder.ServiceGRPC;

import java.util.ArrayList;
import java.util.List;

import com.quark.salesorder.entities.Order;
import com.quark.salesorder.repositories.OrderItemRepository;
import com.quark.salesorder.repositories.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;

import SalesOrderApi.Empty;
import SalesOrderApi.OrderId;
import SalesOrderApi.ItemOrderResponse;
import SalesOrderApi.ItemResponse;
import SalesOrderApi.ItemsOrderResponse;
import SalesOrderApi.OrderReply;
import SalesOrderApi.OrderRequest;
import SalesOrderApi.OrderResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class OrderServiceGRPC extends SalesOrderApi.OrderServiceProtoGrpc.OrderServiceProtoImplBase {
   @Autowired
   private OrderRepository repository;

   public void SendOrder(OrderRequest request, StreamObserver<OrderReply> responseObserver) {
      Integer id = 2;
      OrderReply reply = OrderReply.newBuilder().setId(id).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
   }

   @Override
   public void getOrders(Empty request, StreamObserver<ItemResponse> responseObserver) {
      // TODO Auto-generated method stub
      // super.getOrders(request, responseObserver);
      List<Order> orders = repository.findAll();

      var ordersResponse = new ArrayList<SalesOrderApi.OrderResponse>();
      var itemsOrder = new ArrayList<ItemOrderResponse>();

      for (Order order : orders) {
         // var orderItens = orderItemrepository.findByOrder(order);
         var orderItens = order.getItems();
         // for (OrderItem orderitem : orderItens) {
         // itemsOrder.add(ItemOrderResponse.newBuilder()
         // .setId(orderitem.getId().intValue())
         // .setDescription(orderitem.getDescription())
         // .setPrice(orderitem.getPrice().toString())
         // .setProductId(orderitem.getProductId())
         // .setQuantity(orderitem.getQuantity())
         // .build());
         // }
         var itemsOrderResponse = ItemsOrderResponse.newBuilder().addAllItems(itemsOrder).build();

         ordersResponse.add(OrderResponse.newBuilder()
               .setId(order.getId().intValue())
               .setDescription(order.getDescription())
               .setMoment(order.getMoment().toString())
               .setStatus(order.getOrderStatus().toString())
               .setUserid(order.getUserId())
               .setItems(itemsOrderResponse)
               .build());

      }

      ItemResponse reply = ItemResponse.newBuilder().addAllItems(ordersResponse).build();

      responseObserver.onNext(reply);
      responseObserver.onCompleted();
   }

   @Override
   public void getOrder(OrderId orderId, StreamObserver<OrderResponse> responseObserver) 
   {

      var orderDb = repository.findById((long) orderId.getId());
      var reply = OrderResponse.newBuilder()
            .setId(orderDb.get().getId().intValue())
            .setDescription(orderDb.get().getDescription())
            .setMoment(orderDb.get().getMoment().toString())
            .setStatus(orderDb.get().getOrderStatus().toString())
            .setUserid(orderDb.get().getUserId()).build();

      responseObserver.onNext(reply);
      responseObserver.onCompleted();
   }

}
