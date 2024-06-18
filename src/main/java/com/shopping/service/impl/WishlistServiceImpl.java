package com.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.RegisterDetails;
import com.shopping.entity.Wishlist;
import com.shopping.repository.KidsDressRepository;
import com.shopping.repository.LoginRepository;
import com.shopping.repository.MensDressRepository;
import com.shopping.repository.WomensDressRepository;
import com.shopping.service.WishlistService;

@Service
public class WishlistServiceImpl implements WishlistService{

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private MensDressRepository mensDressRepository;
	
	@Autowired
	private WomensDressRepository womensDressRepository;
	
	@Autowired
	private KidsDressRepository kidsDressRepository;
	
	public void addToWishlist(Long userId, Long dressId, String type) {
		RegisterDetails registerDetails = loginRepository.findById(userId).get();
		if (type.equals("mens")) {
			registerDetails.getWishlist().getMensDress().add(mensDressRepository.findById(dressId).get());
		}
		else if(type.equals("womens")) {
			registerDetails.getWishlist().getWomensDress().add(womensDressRepository.findById(dressId).get());
		}
		else {
			registerDetails.getWishlist().getKidsDress().add(kidsDressRepository.findById(dressId).get());
		}
		loginRepository.save(registerDetails);
	}
	
	public Wishlist getAllWishlist(long userId){
		RegisterDetails registerDetails = loginRepository.findById(userId).get();
		Wishlist wishlists=registerDetails.getWishlist();
		return wishlists;
	}
	
	public void removeFromWishlist(Long userId, Long dressId, String type) {
		RegisterDetails registerDetails = loginRepository.findById(userId).get();
		if (type.equals("mens")) {
			registerDetails.getWishlist().getMensDress().remove(mensDressRepository.findById(dressId).get());
		}
		else if(type.equals("womens")) {
			registerDetails.getWishlist().getWomensDress().remove(womensDressRepository.findById(dressId).get());
		}
		else {
			registerDetails.getWishlist().getKidsDress().remove(kidsDressRepository.findById(dressId).get());
		}
		loginRepository.save(registerDetails);
	}
}
