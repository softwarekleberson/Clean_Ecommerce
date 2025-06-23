package com.cleancode.ecommerce.customer.domain.customer;

import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Delivery extends Address{

	private String deliveryPhrase; 
	
	public Delivery(String deliveryPhrase, String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country) {
		super(receiver, street, number, neighborhood, zipCode, observation, streetType, typeResidence, city, state, country);
		
		if(isValid(deliveryPhrase) || inputSize(deliveryPhrase)) throw new IllegalDomainException("Delivery Phrase is requerid");
		this.deliveryPhrase = deliveryPhrase;
	}
	
	public String getDeliveryPhrase() {
		return deliveryPhrase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(deliveryPhrase);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
		return Objects.equals(deliveryPhrase, other.deliveryPhrase);
	}
}
