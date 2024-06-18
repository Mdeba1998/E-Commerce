package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.entity.Wishlist;
import com.shopping.service.WishlistService;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	@GetMapping("/get-wishlist/{userId}")
	public String getWishlist(@PathVariable Long userId, Model model) {
		System.out.println("WIUI: " + userId);
		Wishlist wishlists = wishlistService.getAllWishlist(userId);

		System.out.println(wishlists.getMensDress().size());
		System.out.println(wishlists.getWomensDress().size());
		System.out.println(wishlists.getKidsDress().size());
		model.addAttribute("wishlist", wishlists);
		return "wishlist";
	}

	@GetMapping("/add-to-wishlist/{userId}/{dressId}")
	public String addToWishlist(@PathVariable Long userId, @PathVariable Long dressId, @RequestParam String type) {
		wishlistService.addToWishlist(userId, dressId, type);
		System.out.println("WIUI: " + userId);
		return "redirect:/wishlist/get-wishlist/" + userId;
	}

	@GetMapping("/remove-from-wishlist/{userId}/{dressId}")
	public String removeFromWishlist(@PathVariable Long userId, @PathVariable Long dressId, @RequestParam String category) {
		wishlistService.removeFromWishlist(userId, dressId, category);
		return "redirect:/wishlist/get-wishlist/" + userId;
	}
}
