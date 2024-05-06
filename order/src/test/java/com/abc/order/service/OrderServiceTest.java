package com.abc.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.order.entity.Order;
import com.abc.order.entity.OrderItem;
import com.abc.order.exception.ResourceNotFoundException;
import com.abc.order.model.OrderResponse;
import com.abc.order.repository.OrderItemRepository;
import com.abc.order.repository.OrderRepository;

@SpringBootTest
public class OrderServiceTest {
	@InjectMocks
	private OrderServiceImpl orderService;
	
	@Mock
	private OrderRepository orderRepository;
	
	@Mock
	private OrderItemRepository orderItemRepository;
	
	
	// get by order id
	
	@Test
	public void testGetAllOrder() {
		Order order= new Order();
		OrderItem orderItem = new OrderItem();
		order.setOrderId(1);
		order.setOrderTotal(100);
		order.setOrderDate(LocalDate.of(2024,05,02));
		order.setOrderStatus("pending");
		orderItem.setOrderItemId(1);
		orderItem.setQuantity(2);
		orderItem.setMedieId(3);
		orderItem.setItemTotal(100);
		
		
		Order order1= new Order();
		OrderItem orderItem1 = new OrderItem();
		order1.setOrderId(3);
		order1.setOrderTotal(270);
		order1.setOrderDate(LocalDate.of(2024,05,03));
		order1.setOrderStatus("pending");
		orderItem1.setOrderItemId(3);
		orderItem1.setQuantity(3);
		orderItem1.setMedieId(4);
		orderItem1.setItemTotal(270);
		
		Order order2= new Order();
		OrderItem orderItem2 = new OrderItem();
		order2.setOrderId(4);
		order2.setOrderTotal(210);
		order2.setOrderDate(LocalDate.of(2024,05,03));
		order2.setOrderStatus("pending");
		orderItem2.setOrderItemId(4);
		orderItem2.setQuantity(6);
		orderItem2.setMedieId(5);
		orderItem2.setItemTotal(210);
		
		List<Order>orders= new ArrayList<>();
		List<OrderItem> orderItems = new ArrayList<>();
		orders.add(order);
		orderItems.add(orderItem);
		orders.add(order1);
		orderItems.add(orderItem1);
		orders.add(order2);
		orderItems.add(orderItem2);
		
	
		when(orderRepository.findAll()).thenReturn(orders);
		  
		List<OrderResponse>orderList=orderService.getAllOrders();
		 
		assertEquals(3,orderList.size());
		 	
	}
	
	@Test
	public void GetOrderDetails() {
		Order order= new Order();
		OrderItem orderItem = new OrderItem();
		order.setOrderId(100);
		order.setOrderTotal(2);
		order.setOrderDate(LocalDate.of(2024,11,11));
		order.setOrderStatus("pending");
		orderItem.setOrderItemId(1);
		orderItem.setQuantity(2);
		orderItem.setMedieId(3);
		orderItem.setItemTotal(100);
		
		when(orderRepository.findById(100)).thenReturn(Optional.of(order));
		
		OrderResponse actualObj=orderService.getOrderDetails(100);
		 
		assertEquals("pending",actualObj.getOrderStatus());
		
	}
	
	@Test
	public void testGetOrderDetailsException() {
		
		when(orderRepository.findById(2)).thenThrow(new ResourceNotFoundException("Resource not existing with id: 100"));
		assertThrows(ResourceNotFoundException.class, ()->orderService.getOrderDetails(1));
	}
	
	@Test
	public void testSaveOrder() {
		Order order3= new Order();
		OrderItem orderItem3 = new OrderItem();
		order3.setOrderId(5);
		order3.setOrderTotal(250);
		order3.setOrderDate(LocalDate.of(2024,05,03));
		order3.setOrderStatus("pending");
		orderItem3.setOrderItemId(3);
		orderItem3.setQuantity(2);
		orderItem3.setMedieId(4);
		orderItem3.setItemTotal(250);
		
		when(orderRepository.save(order3)).thenReturn(order3);
		
		Order savedOrder = orderService.saveOrder(order3);
		
		assertEquals(250, savedOrder.getOrderTotal());
		assertEquals(LocalDate.of(2024,05,03), savedOrder.getOrderDate());
		assertEquals("pending", savedOrder.getOrderStatus());
		
		
		verify(orderRepository, times(1)).save(order3);
	}
	
	@Test
	public void testSaveCustomerWithException() {
		Order order3= new Order();
		OrderItem orderItem3 = new OrderItem();
		order3.setOrderId(5);
		order3.setOrderTotal(250);
		order3.setOrderDate(LocalDate.of(2024,05,03));
		order3.setOrderStatus("pending");
		orderItem3.setOrderItemId(3);
		orderItem3.setQuantity(2);
		orderItem3.setMedieId(4);
		orderItem3.setItemTotal(250);
		
		when(orderRepository.save(order3)).thenThrow(new RuntimeException("Failed to save order"));
		
		assertThrows(RuntimeException.class, () -> orderService.saveOrder(order3));
	}
	
	
	@Test
	public void testUpdateOrder() {
	   
		Order order= new Order();
		OrderItem orderItem = new OrderItem();
		order.setOrderId(1);
		order.setOrderTotal(100);
		order.setOrderDate(LocalDate.of(2024,05,02));
		order.setOrderStatus("pending");
		orderItem.setOrderItemId(1);
		orderItem.setQuantity(2);
		orderItem.setMedieId(3);
		orderItem.setItemTotal(100);

	    // Assume order exists in the database
	    when(orderRepository.findById(1)).thenReturn(Optional.of(order));

	    // Perform the update
	    Order updatedOrder = orderService.updateOrder(order);

	    // Assertions
	    assertEquals(100, updatedOrder.getOrderTotal());
	    // Add more assertions as needed

	    // Verify that the save method was called once
	    verify(orderRepository, times(1)).save(order);
	}

	@Test
	public void testUpdateOrderWithException() {
	    // Create a order object
		
		Order order= new Order();
		OrderItem orderItem = new OrderItem();
		order.setOrderId(1);
		order.setOrderTotal(100);
		order.setOrderDate(LocalDate.of(2024,05,02));
		order.setOrderStatus("pending");
		orderItem.setOrderItemId(1);
		orderItem.setQuantity(2);
		orderItem.setMedieId(3);
		orderItem.setItemTotal(100);

	    // Assume order does not exist in the database
	    when(orderRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the update and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> orderService.updateOrder(order));
	}

	@Test
	public void testDeleteOrderById() {
	    // Create a order object

		Order order= new Order();
		OrderItem orderItem = new OrderItem();
		order.setOrderId(1);
		order.setOrderTotal(100);
		order.setOrderDate(LocalDate.of(2024,05,02));
		order.setOrderStatus("pending");
		orderItem.setOrderItemId(1);
		orderItem.setQuantity(2);
		orderItem.setMedieId(3);
		orderItem.setItemTotal(100);

	    // Assume order with ID 1 exists in the database
	    when(orderRepository.findById(1)).thenReturn(Optional.of(order));

	    // Perform the deletion
	    orderService.deleteOrder(1);

	    // Verify that the delete method was called once with the correct order
	    verify(orderRepository, times(1)).delete(order);
	}

	@Test
	public void testDeleteOrderByIdWithException() {
	    // Assume order with ID 1 does not exist in the database
	    when(orderRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the deletion and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> orderService.deleteOrder(1));
	}
}