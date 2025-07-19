package com.cleancode.ecommerce.customer.infra.gateways;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.CustomerEntity;

public interface CustomerJpa extends JpaRepository<CustomerEntity, UUID> {

	@Query("""
		    SELECT c 
		    FROM CustomerEntity c
		    LEFT JOIN FETCH c.chargeEntities ch
		    LEFT JOIN FETCH c.deliveryEntities de
		    WHERE c.id = :customerId
		""")
	Optional<CustomerEntity> findFullById(@Param("customerId") String customerId);
}
