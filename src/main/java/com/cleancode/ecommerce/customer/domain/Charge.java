package com.cleancode.ecommerce.customer.domain;

public class Charge extends Address{

	public Charge(String receiver, String street, String number, String neighborhood, String zipCode, String observation,
			String streetType, String typeResidence, String city, String state, String country) {
		super(receiver, street, number, neighborhood, zipCode, observation, streetType, typeResidence, city, state, country);
	}
}
