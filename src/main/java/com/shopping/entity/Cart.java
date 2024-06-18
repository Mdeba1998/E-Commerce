package com.shopping.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<MensDress> mensDress = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<WomensDress> womensDress = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<KidsDress> kidsDress = new ArrayList<>();

	@OneToOne(mappedBy = "cart")
	private RegisterDetails registerDetails;

	private OrderStatus orderStatus = OrderStatus.CONFIRMED;

	// Override toString() method to avoid circular references
	@Override
	public String toString() {
		return "Cart{" + "id=" + id + '}';
	}
}
