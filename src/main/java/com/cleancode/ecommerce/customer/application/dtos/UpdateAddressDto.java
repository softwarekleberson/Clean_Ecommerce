package com.cleancode.ecommerce.customer.application.dtos;

public record UpdateAddressDto(String id, String receiver, String street, String number, String neighborhood,
		String zipCode, String observation, String streetType, String typeResidence, String city, String state,
		String country, String deliveryPhrase) {
}
