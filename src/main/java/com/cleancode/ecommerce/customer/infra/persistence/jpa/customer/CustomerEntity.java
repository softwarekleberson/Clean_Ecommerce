package com.cleancode.ecommerce.customer.infra.persistence.jpa.customer;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.ChargeEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.DeliveryEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.UUID)
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
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DeliveryEntity> deliveryEntities;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChargeEntity> chargeEntities;
	
}