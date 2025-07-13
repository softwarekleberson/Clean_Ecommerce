package com.cleancode.ecommerce.customer.infra.persistence.jpa.customer;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.ChargeEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.DeliveryEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class CustomerEntity {

	@Id
	private String id = UUID.randomUUID().toString();
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerEntity other = (CustomerEntity) obj;
		return Objects.equals(id, other.id);
	}
}