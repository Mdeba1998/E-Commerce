package com.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.Cart;
import com.shopping.repository.CartRepository;
import com.shopping.repository.KidsDressRepository;
import com.shopping.repository.LoginRepository;
import com.shopping.repository.MensDressRepository;
import com.shopping.repository.WomensDressRepository;
import com.shopping.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private MensDressRepository mensDressRepository;
	
	@Autowired
	private WomensDressRepository womensDressRepository;
	
	@Autowired
	private KidsDressRepository kidsDressRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	public void addToCart(long userId, long id, String type) {
		Cart cart = loginRepository.findById(userId).get().getCart();
		System.out.println("UI: " + userId + ", type: " + type);
		System.out.println("Cart Id:" + cart.getId());
		if (type.equals("mens"))
			cart.getMensDress().add(mensDressRepository.findById(id).get());
		else if (type.equals("womens"))
			cart.getWomensDress().add(womensDressRepository.findById(id).get());
		else
			cart.getKidsDress().add(kidsDressRepository.findById(id).get());
		System.out.println("CI: " + id + ", type: " + type);

		cartRepository.save(cart);
	}
	
	public void removeFromCart(long userId, long id, String category) {
		System.out.println("Cat: " + category);
		Cart cart = loginRepository.findById(userId).get().getCart();
		if (category.equals("mens"))
			cart.getMensDress().remove(mensDressRepository.findById(id).get());
		else if (category.equals("womens"))
			cart.getWomensDress().remove(womensDressRepository.findById(id).get());
		else
			cart.getKidsDress().remove(kidsDressRepository.findById(id).get());

		cartRepository.save(cart);
	}
}
