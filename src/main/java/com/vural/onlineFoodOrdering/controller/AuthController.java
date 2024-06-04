package com.vural.onlineFoodOrdering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vural.onlineFoodOrdering.config.JwtProvider;
import com.vural.onlineFoodOrdering.repository.CartRepository;
import com.vural.onlineFoodOrdering.repository.UserRepository;
import com.vural.onlineFoodOrdering.service.CustomerUserDetailsService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CustomerUserDetailsService customerUserDetailsService;
	@Autowired
	private CartRepository cartRepository;
	
}
