package com.shopping.service;

public interface CartService {

	public void addToCart(long userId, long id, String type);
	
	public void removeFromCart(long userId, long id, String category);
	
}
