package com.quark.salesorder.ServiceGRPC;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.quark.salesorder.entities.Order;
import com.quark.salesorder.entities.OrderItem;
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
   @Autowired
   private OrderItemRepository orderItemrepository;

   @Override
   public void sendOrder(OrderRequest request, StreamObserver<OrderReply> responseObserver) {

      var order = new Order();
      order.setDescription(request.getDescription());
      order.setMoment(Instant.now());
      order.setOrderStatus(Integer.parseInt(request.getStatus()));
      order.setUserId(request.getUserid());
      var itemsOrderRequest = request.getItems();
      var items = itemsOrderRequest.getItemsList();
      for (var i : items) {
         OrderItem orderItem = new OrderItem(i.getDescription(), i.getQuantity(), Double.parseDouble(i.getPrice()),
               i.getProductId());
         orderItem.getOrders().add(order);
         order.getItems().add(orderItem);
      }

      var entity = repository.save(order);

      Integer id = entity.getId().intValue();
      OrderReply reply = OrderReply.newBuilder().setId(id).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
   }

   @Override
   public void updateOrder(OrderRequest request, StreamObserver<OrderReply> responseObserver) {
      Integer id = request.getId();
      var order = repository.findById(Long.parseLong(id.toString())).get();
      order.setDescription(request.getDescription());
      order.setMoment(Instant.now());
      order.setOrderStatus(Integer.parseInt(request.getStatus()));
      order.setUserId(request.getUserid());

      var itemsOrderRequest = request.getItems();
      var items = itemsOrderRequest.getItemsList();
      for (var item : items) {
         long currentItemId = item.getId();
         var optionalOrderItem = orderItemrepository.findById(currentItemId);
         var orderItemDb = optionalOrderItem.get();
         orderItemDb.setDescription(item.getDescription());
         orderItemDb.setQuantity(item.getQuantity());
         orderItemDb.setPrice(Double.parseDouble(item.getPrice()));
         orderItemDb.setProductId(item.getProductId());
         orderItemrepository.save(orderItemDb);
      }

      var entity = repository.save(order);

      OrderReply reply = OrderReply.newBuilder().setId(entity.getId().intValue()).build();
      responseObserver.onNext(reply);
      responseObserver.onCompleted();
   }

   @Override
   public void getOrders(Empty request, StreamObserver<ItemResponse> responseObserver) {
      // TODO Auto-generated method stub
      // super.getOrders(request, responseObserver);
      List<Order> orders = repository.findAll();

      var ordersResponse = new ArrayList<SalesOrderApi.OrderResponse>();

      for (Order order : orders) {
         var orderItemDb = orderItemrepository.findByOrderId(order.getId().intValue());
         var itemsOrder = new ArrayList<ItemOrderResponse>();

         for (OrderItem orderitem : orderItemDb) {
            itemsOrder.add(ItemOrderResponse.newBuilder()
                  .setId(orderitem.getId().intValue())
                  .setDescription(orderitem.getDescription())
                  .setPrice(orderitem.getPrice().toString())
                  .setProductId(orderitem.getProductId())
                  .setQuantity(orderitem.getQuantity())
                  .build());
         }
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
   public void getOrder(OrderId orderId, StreamObserver<OrderResponse> responseObserver) {

      var orderDb = repository.findById((long) orderId.getId());
      var orderItemDb = orderItemrepository.findByOrderId(orderId.getId());

      var itemsOrder = new ArrayList<ItemOrderResponse>();

      for (OrderItem orderitem : orderItemDb) {
         itemsOrder.add(ItemOrderResponse.newBuilder()
               .setId(orderitem.getId().intValue())
               .setDescription(orderitem.getDescription())
               .setPrice(orderitem.getPrice().toString())
               .setProductId(orderitem.getProductId())
               .setQuantity(orderitem.getQuantity())
               .build());
      }

      var itemsOrderResponse = ItemsOrderResponse.newBuilder().addAllItems(itemsOrder).build();

      var reply = OrderResponse.newBuilder()
            .setId(orderDb.get().getId().intValue())
            .setDescription(orderDb.get().getDescription())
            .setMoment(orderDb.get().getMoment().toString())
            .setStatus(orderDb.get().getOrderStatus().toString())
            .setUserid(orderDb.get().getUserId())
            .setItems(itemsOrderResponse)
            .build();

      responseObserver.onNext(reply);
      responseObserver.onCompleted();
   }

}
