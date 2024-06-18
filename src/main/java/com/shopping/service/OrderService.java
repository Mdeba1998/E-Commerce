package com.shopping.service;

import java.util.List;

import com.shopping.entity.Orders;

public interface OrderService {

	public boolean placeOrders(long userId);
	
	public List<Orders> getAllOrders(long userId);
}
