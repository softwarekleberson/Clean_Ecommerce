package com.cleancode.ecommerce.users.infra.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleancode.ecommerce.customer.application.dtos.customer.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomer;
import com.cleancode.ecommerce.shared.config.security.CustomUserDetailsService;
import com.cleancode.ecommerce.shared.config.security.JwtService;
import com.cleancode.ecommerce.users.application.contract.CreateNewAdm;
import com.cleancode.ecommerce.users.application.dto.adm.CreateAdmDto;
import com.cleancode.ecommerce.users.application.dto.user.JwtResponse;
import com.cleancode.ecommerce.users.application.dto.user.LoginRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private final CustomUserDetailsService userDetailsService;
	private final CreateNewAdm createNewAdm;
	private final CreateCustomer createCustomer;

	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
			CustomUserDetailsService userDetailsService, CreateNewAdm createNewAdm, CreateCustomer createCustomer) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.userDetailsService = userDetailsService;
		this.createNewAdm = createNewAdm;
		this.createCustomer = createCustomer;
	}

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

		UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
		String jwtToken = jwtService.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(jwtToken));
	}

	@PostMapping("/adm")
	public ResponseEntity<Void> createAdm(@Valid @RequestBody CreateAdmDto dto) {
		createNewAdm.execute(dto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PostMapping("/custumer")
	public ResponseEntity<ListCustomerDto> createCustomer(@Valid @RequestBody CreateCustomerDto dto) {
		var created = createCustomer.execute(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}
}