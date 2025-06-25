package com.cleancode.ecommerce.customer.infra.persistence;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String cpf;
	private boolean active;
	private String name;
	private LocalDate birth;
	private String password;
	@Enumerated(EnumType.STRING)
	private GenderEntity gender;
	@Embedded
	private PhoneEntity phone;
	@Embedded
	private EmailEntity email;
	
	public CustomerEntity() {
	}

	public CustomerEntity(String id, String cpf, boolean active, String name, LocalDate birth, String password,
			GenderEntity gender, PhoneEntity phone, EmailEntity email) {
		this.id = id;
		this.cpf = cpf;
		this.active = active;
		this.name = name;
		this.birth = birth;
		this.password = password;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public GenderEntity getGender() {
		return gender;
	}

	public void setGender(GenderEntity gender) {
		this.gender = gender;
	}

	public PhoneEntity getPhone() {
		return phone;
	}

	public void setPhone(PhoneEntity phone) {
		this.phone = phone;
	}

	public EmailEntity getEmail() {
		return email;
	}

	public void setEmail(EmailEntity email) {
		this.email = email;
	}
}