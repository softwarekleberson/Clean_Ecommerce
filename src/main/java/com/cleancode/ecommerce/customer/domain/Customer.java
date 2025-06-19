package com.cleancode.ecommerce.customer.domain;

import java.util.List;

import com.cleancode.ecommerce.customer.shared.domain.Cpf;

import java.util.ArrayList;

public class Customer {

	private CustomerId id;
	private boolean active = false;
	private Name name;
	private Gender gender;
	private Birth birth;
	private Cpf cpf;
	private Contact contact;
	private Password password;
	private List<Delivery> deliverys = new ArrayList<>();
	private List<Charge> charges = new ArrayList<>();
	
	public Customer(CustomerId id, Name name, Gender gender, Birth birth, Cpf cpf, Contact contact, Password password) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.cpf = cpf;
		this.contact = contact;
		this.password = password;
	}

	public void updateActivationStatus() {
		boolean isCharge = !charges.isEmpty();
		boolean isDelivery = !deliverys.isEmpty();
		
		this.active = isCharge && isDelivery;
	}
	
	public String getEmail() {
		return this.contact.getEmail();
	}
	
	public String getFullPhone() {
		return this.contact.getFullPhone();
	}
	
	public void insertNewDelivery(Delivery delivery) {
		this.deliverys.add(delivery);
	}
	
	public Delivery getDeliveryIndex(int index) {
		int position = deliverys.size() - 1;
		position -= index;

		if(position < 0 || position >= deliverys.size()) {
			throw new IndexOutOfBoundsException("Invalid Index : " + index);
		}
		
		return this.deliverys.get(position);
	}
	
	public void insertNewCharge(Charge charge) {
		this.charges.add(charge);
	}
	
	public Charge getChargeIndex(int index) {
		int position = charges.size() - 1;
		position -= index;
		
		if(position < 0 || position >= charges.size()) {
			throw new IndexOutOfBoundsException("Invalid Index : " + index);
		}
		
		return this.charges.get(position);
	}

	public boolean isActive() {
		return active;
	}
	
    public CustomerId getId() {
        return id;
    }

	public Name getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public Birth getBirth() {
		return birth;
	}

	public Cpf getCpf() {
		return cpf;
	}

	public Contact getContact() {
		return contact;
	}

	public List<Delivery> getDeliverys() {
		return deliverys;
	}

	public List<Charge> getCharges() {
		return charges;
	}
}
