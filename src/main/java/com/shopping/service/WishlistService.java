package com.shopping.service;

import com.shopping.entity.Wishlist;

public interface WishlistService {

	public void addToWishlist(Long userId, Long dressId, String type);
	
	public Wishlist getAllWishlist(long userId);
	
	public void removeFromWishlist(Long userId, Long dressId, String type);
	
}
