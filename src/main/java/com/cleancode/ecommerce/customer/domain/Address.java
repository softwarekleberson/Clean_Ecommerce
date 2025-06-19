package com.cleancode.ecommerce.customer.domain;

import java.util.Objects;

public abstract class Address {

	protected String receiver;
	protected String street;
	protected String number;
	protected String neighborhood;
	
	protected String zipCode;
	protected String observation;
	protected String streetType;
	protected String typeResidence;
	protected String city;
	protected String state;
	protected String country;
	
	public Address(String receiver, String street, String number, String neighborhood, String zipCode, String observation,
			String streetType, String typeResidence, String city, String state, String country) {
		super();
		this.receiver = receiver;
		this.street = street;
		this.number = number;
		this.neighborhood = neighborhood;
		this.zipCode = zipCode;
		this.observation = observation;
		this.streetType = streetType;
		this.typeResidence = typeResidence;
		this.city = city;
		this.state = state;
		this.country = country;
	}

	public String getReceiver() {
		return receiver;
	}

	public String getStreet() {
		return street;
	}

	public String getNumber() {
		return number;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getObservation() {
		return observation;
	}

	public String getStreetType() {
		return streetType;
	}

	public String getTypeResidence() {
		return typeResidence;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	@Override
	public int hashCode() {
		return Objects.hash(zipCode, city, country, neighborhood, number, observation, receiver, state, street, streetType,
				typeResidence);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(zipCode, other.zipCode) && Objects.equals(city, other.city)
				&& Objects.equals(country, other.country) && Objects.equals(neighborhood, other.neighborhood)
				&& Objects.equals(number, other.number) && Objects.equals(observation, other.observation)
				&& Objects.equals(receiver, other.receiver) && Objects.equals(state, other.state)
				&& Objects.equals(street, other.street) && Objects.equals(streetType, other.streetType)
				&& Objects.equals(typeResidence, other.typeResidence);
	}

	@Override
	public String toString() {
		return "Address [receiver=" + receiver + ", street=" + street + ", number=" + number + ", neighborhood="
				+ neighborhood + ", zipCode=" + zipCode + ", observation=" + observation + ", streetType=" + streetType
				+ ", typeResidence=" + typeResidence + ", city=" + city + ", state=" + state + ", country=" + country
				+ "]";
	}
}
