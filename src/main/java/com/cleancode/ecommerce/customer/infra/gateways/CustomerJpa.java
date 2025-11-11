package com.cleancode.ecommerce.customer.infra.gateways;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.CustomerEntity;

public interface CustomerJpa extends JpaRepository<CustomerEntity, String> {

	@Query("""
			    SELECT c
			    FROM CustomerEntity c
			    LEFT JOIN FETCH c.chargeEntities ch
			    LEFT JOIN FETCH c.deliveryEntities de
			    WHERE c.id = :customerId
			""")
	Optional<CustomerEntity> findFullById(@Param("customerId") String customerId);

	@Query("""
			    SELECT c
			    FROM CustomerEntity c
			""")
	List<CustomerEntity> findAllCustomer();

    Optional<CustomerEntity> findByEmail_Email(String email);
}
