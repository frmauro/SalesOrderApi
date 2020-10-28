package com.quark.salesorder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.quark.salesorder.repositories.OrderItemRepository;
import com.quark.salesorder.repositories.OrderRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SalesorderApplicationTests {

	@Autowired
    private OrderRepository orderRepository;
    
    @Autowired
	private OrderItemRepository orderItemRepository;

	@Test
	void contextLoads() {
		String t1 = "TESTE";
		String t2 = "TESTE";
		assertEquals(t1, t2);  // JUnit assertion
	}

}
