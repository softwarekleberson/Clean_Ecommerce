package com.cleancode.ecommerce.customer.domain;

import com.cleancode.ecommerce.customer.domain.exception.IllegalDomainException;

public class Delivery extends Address{

	private String deliveryPhrase; 
	
	public Delivery(String deliveryPhrase, String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state, String country) {
		super(receiver, street, number, neighborhood, zipCode, observation, streetType, typeResidence, city, state, country);
		
		if(isValid(deliveryPhrase)) throw new IllegalDomainException("Delivery Phrase is requerid");
		
		this.deliveryPhrase = deliveryPhrase;
	}
	
	public String getDeliveryPhrase() {
		return deliveryPhrase;
	}
}
