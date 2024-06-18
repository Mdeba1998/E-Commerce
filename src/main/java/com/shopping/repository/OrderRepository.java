package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.entity.Orders;
import com.shopping.entity.RegisterDetails;



public interface OrderRepository extends JpaRepository<Orders, Long>{

	List<Orders> findAllById(Long userId);
	
	List<Orders> findAllByRegisterDetails(RegisterDetails registerDetails);

}
