package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.entity.Cart;
import com.shopping.entity.Orders;
import com.shopping.service.AdminService;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/login-google")
	public String googleLogin(Model model) {
		model.addAttribute("isLoggedIn", true);
		return "profile";
	}

	@GetMapping("/profile")
	public String getForm(Model model) {

		return "profile";
	}

	@GetMapping("/profile1")
	public String getProfile(Model model) {

		return "profile1";
	}

	@GetMapping("/get-orders")
	public String getOrders(Model model) {
		List<Orders> carts = adminService.getAllOrders();
		model.addAttribute("orders", carts);
		return "orders";
	}

	@GetMapping("/update-status/{orderId}/{status}")
	public String updateOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
		System.out.println("OS: "+status);
		System.out.println("OI: "+orderId);
		adminService.updateStatus(orderId, status);
		System.out.println("Status Updated...");
		return "redirect:/admin/get-orders";
	}

}
