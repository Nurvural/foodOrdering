package com.vural.onlineFoodOrdering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vural.onlineFoodOrdering.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String username);
}
