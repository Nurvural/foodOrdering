package com.vural.onlineFoodOrdering.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Restaurant {
	/*
	 * name; description; cuisineType; address; contactInformation; openingHours;
	 * reviews; orders; numRating; images; registrationDate; open; foods;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	private User owner;

	private String name;
	private String description;
	private String cuisineType;

	private String openingHourse;

	@Column(length = 1000)
	@ElementCollection
	private List<String> images;

	private LocalDateTime registrationDate;

	private boolean open;

	@OneToOne
	private Address address;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant", orphanRemoval = true)
	private List<Order> orders = new ArrayList<Order>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private List<Food> foods;
}
