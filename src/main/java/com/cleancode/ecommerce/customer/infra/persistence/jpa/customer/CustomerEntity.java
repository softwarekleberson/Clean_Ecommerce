package com.cleancode.ecommerce.customer.infra.persistence.jpa.customer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.ChargeEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.DeliveryEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID id;
	private String cpf;
	private boolean active;
	private String name;
	private LocalDate birth;
	private String password;
	
	@Enumerated(EnumType.STRING)
	private GenderEntity gender;
	
	@Embedded
	private PhoneEntity phone;
	
	@Embedded
	private EmailEntity email;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<DeliveryEntity> deliveryEntities = new HashSet<>();

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<ChargeEntity> chargeEntities = new HashSet<>();

}