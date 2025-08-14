package com.cleancode.ecommerce.cart.infra.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.cart.infra.persistence.CartEntity;

public interface CartJpa extends JpaRepository<CartEntity, String>{

}
