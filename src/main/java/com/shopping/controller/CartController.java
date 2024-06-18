package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.repository.LoginRepository;
import com.shopping.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private CartService cartService;

	@GetMapping("/getcart/{id}")
	public String getCart(@PathVariable Long id, Model model) {
		model.addAttribute("cart", loginRepository.findById(id).get().getCart());
		return "cart";
	}

	@GetMapping("/add-to-cart/{userId}/{id}")
	public String getMethodName(@PathVariable Long userId, @PathVariable Long id, @RequestParam String type) {
		cartService.addToCart(userId, id, type);
		return "redirect:/" + type + "/dresses";
	}

	@GetMapping("/remove-from-cart/{userId}/{id}")
	public String removeFromCart(@PathVariable Long userId, @PathVariable Long id, @RequestParam String category) {
		cartService.removeFromCart(userId, id, category);
		return "redirect:/cart/getcart/" + userId;
	}

	@GetMapping("/update-quantity/{userId}/{productId}")
	public void updateQuantity(@PathVariable Long userId, @PathVariable Long productId, @RequestParam String type,
			@RequestParam Long quantity) {
		System.out.println("UI: " + userId);
		System.out.println("PI: " + productId);
		System.out.println("PT: " + type);
		System.out.println("QTY: " + quantity);
	}

}
