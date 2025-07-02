package com.cleancode.ecommerce.customer.infra.gateways;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.customer.infra.persistence.jpa.CustomerEntity;

interface CustomerRepositoryJpa extends JpaRepository<CustomerEntity, UUID>{

}
