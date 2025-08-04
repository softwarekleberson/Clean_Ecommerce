package com.cleancode.ecommerce.customer.domain.customer;

import java.util.List;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Customer {

	private IdCustomer id;
	private boolean active = false;
	private Name name;
	private Gender gender;
	private Birth birth;
	private Cpf cpf;
	private Contact contact;
	private Password password;
	private List<Delivery> deliveries = new ArrayList<>();
	private List<Charge> charges = new ArrayList<>();

	public Customer(IdCustomer id, Name name, Gender gender, Birth birth, Cpf cpf, Contact contact, Password password) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.cpf = cpf;
		this.contact = contact;
		this.password = password;
	}

	public void assignId(String id) {
		this.id = new IdCustomer(id);
	}

	public void updateCustomer(String name, LocalDate birth, String ddd, String phone, TypePhone typePhone) {
		if (name != null && !name.isBlank()) {
			this.name = new Name(name);
		}

		if (birth != null) {
			this.birth = new Birth(birth);
		}

		if (ddd != null && !ddd.isBlank()) {
			this.contact = new Contact(new Phone(ddd, this.contact.getPhone(), this.contact.getTypePhone()),
					contact.getEmail());
		}

		if (phone != null && !phone.isBlank()) {
			this.contact = new Contact(new Phone(this.contact.getDDD(), phone, this.getContact().getTypePhone()),
					contact.getEmail());
		}

		if (typePhone != null) {
			this.contact = new Contact(new Phone(this.contact.getDDD(), this.contact.getPhone(), typePhone),
					contact.getEmail());
		}
	}

	public void updatePassword(String password) {
		if (password != null && !password.isBlank()) {
			this.password = new Password(password);
		}
	}

	private boolean meetsActivationCriteria() {
		return !charges.isEmpty() && !deliveries.isEmpty();
	}

	public boolean checkActivationRequirements() {
		this.active = meetsActivationCriteria();
		return this.active;
	}

	public Email getEmail() {
		return this.contact.getEmail();
	}

	public Phone getFullPhone() {
		return this.contact.getFullPhone();
	}

	public void registerDelivery(Delivery delivery) {
		this.deliveries.add(delivery);
	}

	public Delivery findDeliveryById(String id) {
		if (id == null || id.isBlank()) {
			throw new IllegalDomainException("Delivery ID must not be null or blank");
		}

		return deliveries.stream().filter(d -> d.getId().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalDomainException("Id Delivery not found"));
	}

	public void removeDeliveryById(String id) {
		if (id == null || id.isBlank() || this.deliveries == null) {
			throw new IllegalDomainException(
					"Cannot remove delivery: id is null/empty or delivery list is not initialized");
		}

		this.deliveries.removeIf(d -> d.getId().equals(id));
	}

	public void registerCharge(Charge charge) {
		this.charges.add(charge);
	}

	public Charge findChargeById(String id) {
		if (id == null || id.isBlank()) {
			throw new IllegalDomainException("Charge ID must not be null or blank");
		}

		return charges.stream().filter(c -> c.getId().equals(id)).findFirst()
				.orElseThrow(() -> new IllegalDomainException("Id Charge not found"));
	}

	public void removeChargeById(String id) {
		if (id == null || id.isBlank() || this.charges == null) {
			throw new IllegalDomainException(
					"Cannot remove charge: id is null/empty or Charge list is not initialized");
		}

		this.charges.removeIf(c -> id.equals(c.getId()));
	}

	public boolean isActive() {
		return active;
	}

	public IdCustomer getId() {
		return id;
	}

	public Name getName() {
		return name;
	}

	public Password getPassword() {
		return password;
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