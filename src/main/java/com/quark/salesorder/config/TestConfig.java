package com.quark.salesorder.config;

// import java.time.Instant;
// import java.util.Arrays;

// import com.quark.salesorder.entities.Order;
// import com.quark.salesorder.entities.OrderItem;
// import com.quark.salesorder.repositories.OrderItemRepository;
// import com.quark.salesorder.repositories.OrderRepository;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    // @Autowired
    // private OrderRepository orderRepository;
    
    // @Autowired
	// private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        
        // var o1 = new Order(null, "Order 001", Instant.parse("2020-07-20T19:53:07Z"), 1, 1);
        // var o2 = new Order(null, "Order 004", Instant.parse("2020-08-20T19:53:07Z"), 2, 2);
        // var o3 = new Order(null, "Order 003", Instant.parse("2020-09-20T19:53:07Z"), 2, 1);

        // orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        // OrderItem oi1 = new OrderItem("Product 001", 200, 230.0, 1);
		// OrderItem oi2 = new OrderItem("Product 002", 300, 120.0, 2);
		// OrderItem oi3 = new OrderItem("Product 003", 400, 280.0, 3);
		// OrderItem oi4 = new OrderItem("Product 002", 600, 250.0, 1); 
		
		// orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

    }

}
