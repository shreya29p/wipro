package com.abc.order.payload;

import java.util.ArrayList;
import java.util.List;

public class OrderPayload {

	private int userId;	
	private List<OrderItemPayload> orderItems = new ArrayList<>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<OrderItemPayload> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemPayload> orderItems) {
		this.orderItems = orderItems;
	}	
	
}
