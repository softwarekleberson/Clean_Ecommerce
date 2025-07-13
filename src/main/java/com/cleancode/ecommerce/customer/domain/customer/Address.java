package com.cleancode.ecommerce.customer.domain.customer;

import java.util.Objects;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public abstract class Address {

	protected static final int LENGTH_MAX = 255;
	
	protected final String id;
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
	
	public Address(String id, String receiver, String street, String number, String neighborhood, String zipCode, String observation,
			String streetType, String typeResidence, String city, String state, String country) {
		
		validateInput(receiver, street, number, neighborhood,
					  zipCode, observation, streetType, typeResidence,
					  city,  state,  country);
		
		this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
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

	private void validateInput(String receiver, String street, String number, String neighborhood, String zipCode,
			String observation, String streetType, String typeResidence, String city, String state,
			String country) {
		
		if(isValid(receiver)) throw new IllegalDomainException("Receiver is requerid");
		if(isValid(street)) throw new IllegalDomainException("Street is requerid");
		if(isValid(number)) throw new IllegalDomainException("Number is requerid");
		if(isValid(neighborhood)) throw new IllegalDomainException("Neighborhood is requerid");
		if(isZipCode(zipCode)) throw new IllegalDomainException("Zip code must be in the format xxxxx-xxx");
		if(inputSize(observation) || isValid(observation)) throw new IllegalDomainException("Observation needs max length 400 caracters");
		if(isValid(streetType)) throw new IllegalDomainException("Street type is requerid");
		if(isValid(typeResidence)) throw new IllegalDomainException("Type Residence is requerid");
		if(isValid(city)) throw new IllegalDomainException("City is requerid");
		if(isValid(state)) throw new IllegalDomainException("State is requerid");
		if(isValid(country)) throw new IllegalDomainException("Country is requerid");
	}

	protected boolean inputSize(String value) {
		return value.length() > LENGTH_MAX;
	}

	protected boolean isZipCode(String zipCode) {
	    String zipCodeRegex = "^\\d{5}-\\d{3}$"; 
		return !zipCode.matches(zipCodeRegex);
	}

	protected boolean isValid(String value) {
		return value == null || value.trim().isEmpty();
	}

	public String getId() {
		return id;
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
