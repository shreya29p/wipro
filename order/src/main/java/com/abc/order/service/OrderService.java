package com.abc.order.service;

import java.util.List;

import com.abc.order.entity.Order;
import com.abc.order.model.OrderResponse;
import com.abc.order.payload.OrderItemPayload;


public interface OrderService {
	
	Order createOrder(int userId, List<OrderItemPayload> selectedMedicines);

	Order saveOrder(Order order);
	
	OrderResponse getOrderDetails(int orderId);
	
	List<OrderResponse> getAllOrders();
	
	Order updateOrder(Order order);

	void deleteOrder(int orderId);
	
//	List<Order> getAllOrdersByCustomer(customerId);
}
