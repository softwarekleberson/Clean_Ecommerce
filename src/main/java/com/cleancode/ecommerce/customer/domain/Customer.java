package com.cleancode.ecommerce.customer.domain;

import java.util.List;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.shared.domain.Cpf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
		if(deliverys.stream().anyMatch(d -> d.equals(delivery))) {
			throw new IllegalDomainException("This address was previously registered");
		}
		this.deliverys.add(delivery);
	}
	
	public Delivery getDelivery(UUID id) {
		return deliverys.stream()
			   .filter(d -> d.getId().equals(id))
			   .findFirst()
			   .orElseThrow(() -> new IllegalDomainException("Id Delivery not found"));
	}
	
	public void removeDelivery(UUID id) {
		this.deliverys.removeIf(d -> d.getId().equals(id));
	}
	
	public void insertNewCharge(Charge charge) {
		if(charges.stream().anyMatch(c->c.equals(charge))) {
			throw new IllegalDomainException("This address was previously registered");
		}
		this.charges.add(charge);
	}
	
	public Charge getCharge(UUID id) {
		return charges.stream()
				.filter(c -> c.getId().equals(id))
				.findFirst()
				.orElseThrow(() -> new IllegalDomainException("Id Charge not found"));
	}
	
	public void removeCharge(UUID id) {
		this.charges.removeIf(c -> c.getId().equals(id));
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
		return Collections.unmodifiableList(this.deliverys);
	}

	public List<Charge> getCharges() {
		return Collections.unmodifiableList(this.charges);
	}
}
