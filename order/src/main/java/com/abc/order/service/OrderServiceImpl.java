package com.abc.order.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.order.entity.Order;
import com.abc.order.entity.OrderItem;
import com.abc.order.exception.ResourceNotFoundException;
import com.abc.order.model.User;
import com.abc.order.model.Medicine;
import com.abc.order.model.OrderItemResponse;
import com.abc.order.model.OrderResponse;
import com.abc.order.payload.OrderItemPayload;
import com.abc.order.repository.OrderRepository;



@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MedicineServiceConsumer medicineService ;
	
	@Autowired
	private UserServiceConsumer userService ;

	
	@Override
	public Order saveOrder(Order order) {
		orderRepository.save(order);
		return order;
	}

	@Override
	public OrderResponse getOrderDetails(int orderId) {	
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new ResourceNotFoundException("Order not found with id: "+orderId);
		}		
		Order order = optionalOrder.get();	
		
		OrderResponse orderResponse= new OrderResponse();
		orderResponse.setOrderId(order.getOrderId());
		orderResponse.setOrderDate(order.getOrderDate());
		orderResponse.setOrderTotal(order.getOrderTotal());
		orderResponse.setOrderStatus(order.getOrderStatus());
		
		int userId=order.getUserId();
		User user=userService.getUserDetailsById(userId);
		
		orderResponse.setUser(user);
		
		List<OrderItemResponse> orderItems= new ArrayList<>();
		
		List<OrderItem> oitems=order.getOrderItems();
		for(OrderItem oi :oitems) {
			OrderItemResponse oitemResp= new OrderItemResponse();
			oitemResp.setOrderItemId(oi.getOrderItemId());
			oitemResp.setItemTotal(oi.getItemTotal());
			oitemResp.setQuantity(oi.getQuantity());
			
			int mid=oi.getMedieId();
			Medicine medicine=medicineService.getMedicineById(mid);
			oitemResp.setMedicine(medicine);
			
			orderItems.add(oitemResp);
		}
		orderResponse.setOrderItems(orderItems);
		return orderResponse;
	}
	
	
	
	@Override
	public List<OrderResponse> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderResponse> orderResponses = new ArrayList<>();
		
		for( Order order : orders) {
			OrderResponse orderResponse = new OrderResponse();
			orderResponse.setOrderId(order.getOrderId());
			orderResponse.setOrderDate(order.getOrderDate());
			orderResponse.setOrderTotal(order.getOrderTotal());
			orderResponse.setOrderStatus(order.getOrderStatus());
			
			int userId = order.getUserId();
			User user = userService.getUserDetailsById(userId);
			
			orderResponse.setUser(user);
			
			List<OrderItemResponse> orderItems = new ArrayList<>();
			
			List<OrderItem> oitemList = order.getOrderItems();
			
			for(OrderItem oi : oitemList) {
				OrderItemResponse oitemResp = new OrderItemResponse();
				oitemResp.setOrderItemId(oi.getOrderItemId());
				oitemResp.setItemTotal(oi.getItemTotal());
				oitemResp.setQuantity(oi.getQuantity());
				
				int mid = oi.getMedieId();
				Medicine medicine = medicineService.getMedicineById(mid);
				oitemResp.setMedicine(medicine);
				
				orderItems.add(oitemResp);
			}
		
			orderResponse.setOrderItems(orderItems);
			orderResponses.add(orderResponse);
		}
				return orderResponses;
	
	}



	@Override
	public Order createOrder(int userId, List<OrderItemPayload> selectedMedicines) {
		
		User user = userService.getUserDetailsById(userId);
		
		Order order = new Order();
		order.setOrderDate(LocalDate.now());
		order.setOrderStatus("pending");
		order.setUserId(userId);
		
		List<OrderItem> orderItems = new ArrayList<>();		

		double orderTotal = 0;	
		
		for(OrderItemPayload po: selectedMedicines)  {			
			int medieId = po.getMedieId();
			int qty = po.getQuantity();			
			Medicine medicine = medicineService.getMedicineById(medieId);
			System.out.println("Itemtotal: "+medicine.getMediPrice()*qty);
			
			OrderItem orderItem = new OrderItem();
			orderItem.setMedieId(medieId);
			orderItem.setItemTotal(medicine.getMediPrice()*qty);			
			orderItem.setQuantity(qty);			
			orderItems.add(orderItem);
			
			orderTotal = orderTotal+(medicine.getMediPrice()*qty);
			
			orderItem.setOrder(order);		
		};
		
		order.setOrderTotal(orderTotal);
		order.setOrderItems(orderItems);
		return order;
	}

	@Override
	public Order updateOrder(Order order) {
		Optional<Order> optionalOrder = orderRepository.findById(order.getOrderId());
        if(optionalOrder.isEmpty()) {
        	throw new ResourceNotFoundException("Order not existing with id: "+order.getOrderId());
        }
        orderRepository.save(order);
        return order;
	}

	@Override
	public void deleteOrder(int orderId) {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()) {
        	throw new ResourceNotFoundException("Order not existing with id: "+orderId);
        }
        Order order = optionalOrder.get();
        orderRepository.delete(order);
		
	}

}