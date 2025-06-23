package com.cleancode.ecommerce.customer.domain.customer;

import java.util.List;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Customer {

	private Id id;
	private boolean active = false;
	private Name name;
	private Gender gender;
	private Birth birth;
	private Cpf cpf;
	private Contact contact;
	private Password password;
	private List<Delivery> deliveries = new ArrayList<>();
	private List<Charge> charges = new ArrayList<>();

	public Customer(Id id, Name name, Gender gender, Birth birth, Cpf cpf, Contact contact, Password password) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.cpf = cpf;
		this.contact = contact;
		this.password = password;
	}

	public void updateCustomer(String name, LocalDate birth, String ddd, String phone, TypePhone typePhone) {
		if (name != null && !name.isBlank()) {
			this.name = new Name(name);
		}

		if (birth != null) {
			this.birth = new Birth(birth);
		}

		if (ddd != null && ddd.isBlank()) {
			this.contact = new Contact(new Phone(ddd, this.contact.getPhone(), this.contact.getTypePhone()),
			contact.getEmail());
		}
		
		if(phone != null && phone.isBlank()) {
			this.contact = new Contact(new Phone(this.contact.getDDD(), phone, this.getContact().getTypePhone()),
			contact.getEmail());
		}
		
		if(typePhone != null) {
			this.contact = new Contact(new Phone(this.contact.getDDD(), this.contact.getPhone(), typePhone),
			contact.getEmail());
		}
	}

	public void updateActivationStatus() {
		boolean isCharge = !charges.isEmpty();
		boolean isDelivery = !deliveries.isEmpty();

		this.active = isCharge && isDelivery;
	}

	public Email getEmail() {
		return this.contact.getEmail();
	}

	public Phone getFullPhone() {
		return this.contact.getFullPhone();
	}

	public void insertNewDelivery(Delivery delivery) {
		if (deliveries.stream().anyMatch(d -> d.equals(delivery))) {
			throw new IllegalDomainException("This address was previously registered");
		}
		this.deliveries.add(delivery);
	}

	public Delivery getDelivery(UUID id) {
		return deliveries.stream().filter(d -> d.getId().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalDomainException("Id Delivery not found"));
	}

	public void removeDelivery(UUID id) {
		this.deliveries.removeIf(d -> d.getId().equals(id));
	}

	public void insertNewCharge(Charge charge) {
		if (charges.stream().anyMatch(c -> c.equals(charge))) {
			throw new IllegalDomainException("This address was previously registered");
		}
		this.charges.add(charge);
	}

	public Charge getCharge(UUID id) {
		return charges.stream().filter(c -> c.getId().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalDomainException("Id Charge not found"));
	}

	public void removeCharge(UUID id) {
		this.charges.removeIf(c -> c.getId().equals(id));
	}

	public boolean isActive() {
		return active;
	}

	public Id getId() {
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
		return Collections.unmodifiableList(this.deliveries);
	}

	public List<Charge> getCharges() {
		return Collections.unmodifiableList(this.charges);
	}
}
