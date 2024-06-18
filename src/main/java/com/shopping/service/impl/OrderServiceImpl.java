package com.shopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.Cart;
import com.shopping.entity.KidsDress;
import com.shopping.entity.MensDress;
import com.shopping.entity.Orders;
import com.shopping.entity.RegisterDetails;
import com.shopping.entity.WomensDress;
import com.shopping.repository.CartRepository;
import com.shopping.repository.LoginRepository;
import com.shopping.repository.OrderRepository;
import com.shopping.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private OrderRepository orderRepository;

	public boolean placeOrders(long userId) {

		RegisterDetails user = loginRepository.findById(userId).orElse(null);

		if (user == null) {
			// Handle the case where the user is not found
			return false;
		}

		Cart cart = user.getCart();
		for (MensDress mensDress : cart.getMensDress()) {
			Orders order = new Orders();
			order.setMensDress(mensDress);
			order.setRegisterDetails(user);

			// Save the new order
			orderRepository.save(order);
		}

		for (WomensDress womensDress:cart.getWomensDress()) {
			Orders order = new Orders();
			order.setWomensDress(womensDress);
			order.setRegisterDetails(user);

			// Save the new order
			orderRepository.save(order);
		}

		for (KidsDress kidsDress:cart.getKidsDress()) {
			Orders order = new Orders();
			order.setKidsDress(kidsDress);
			order.setRegisterDetails(user);

			// Save the new order
			orderRepository.save(order);
		}

		// Update the user's cart in the repository
		cartRepository.save(cart);

		// Update the user in the repository by assigning him a new cart so that after
		// placing order successfully cart items will be empty
		user.setCart(new Cart());
		loginRepository.save(user);

		return true;
	}

	public List<Orders> getAllOrders(long userId) {
		RegisterDetails user = loginRepository.findById(userId).get();

		List<Orders> orders = orderRepository.findAllByRegisterDetails(user);

		System.out.println("Size: "+orders.size());
//		orders.stream().map(o->o.getMensDress()).forEach(System.out::println);
//		orders.stream().map(cart -> cart.getOrderStatus()).forEach(System.out::println);

		return orders;
	}
}
