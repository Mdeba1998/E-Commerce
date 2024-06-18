package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.OrderStatus;
import com.shopping.entity.Orders;
import com.shopping.repository.CartRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}
	
	public void updateStatus(long orderId, String status) {
		OrderStatus orderStatus = OrderStatus.valueOf(status);
		Orders orders = orderRepository.findById(orderId).get();
		orders.setOrderStatus(orderStatus);
		orderRepository.save(orders);
	}
}
