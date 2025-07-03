package com.cleancode.ecommerce.customer.infra.persistence.jpa.address;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery")
public class DeliveryEntity extends AdrressEntity {

	@Column(name = "delivery_phrase")
	private String deliveryPhrase;
}
