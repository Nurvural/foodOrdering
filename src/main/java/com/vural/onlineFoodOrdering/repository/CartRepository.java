package com.vural.onlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vural.onlineFoodOrdering.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
