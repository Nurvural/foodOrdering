package com.vural.onlineFoodOrdering.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int quantity;

	@ManyToOne
	@JsonIgnore
	private Cart cart;

	@ManyToOne
	private Food food;

	private List<String> ingredients;

	private Long totalPrice;
}
