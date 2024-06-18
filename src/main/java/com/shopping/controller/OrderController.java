package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.entity.Orders;
import com.shopping.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/place-orders/{userId}")
	@Transactional
	public String placeOrders(@PathVariable Long userId) {
		boolean status = orderService.placeOrders(userId);
		if (status)
			return "redirect:/orders/all-orders/" + userId;
		else
			return "error";
	}

	@GetMapping("/all-orders/{userId}")
	public String getOrders(@PathVariable Long userId, Model model) {
		List<Orders> orders = orderService.getAllOrders(userId);
		model.addAttribute("orders", orders);
		return "orders";
	}
}
