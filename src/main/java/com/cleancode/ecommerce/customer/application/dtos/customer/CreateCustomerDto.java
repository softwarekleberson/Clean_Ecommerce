package com.cleancode.ecommerce.customer.application.dtos.customer;

import java.time.LocalDate;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.SystemClientStatus;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCustomerDto {

	private String name;

	@NotNull(message = "MALE, WOMAN, NOT_INFORMED")
	private Gender gender;

	@NotNull
	private LocalDate birth;

	@NotBlank(message = "The CPF needs this format : xxx.xxx.xxx-xx")
	private String cpf;

	@NotBlank(message = "Ddd need two digits")
	private String ddd;

	@NotBlank(message = "The Phone needs this format : xxxxxxxxx")
	private String phone;

	@NotNull(message = "MOBILE, WHATSAPP, FIXED")
	private TypePhone typePhone;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank(message = "Confirm Password need be equals Password")
	private String confirmPassword;

	public CreateCustomerDto(String name, Gender gender, LocalDate birth, String cpf, String ddd, String phone,
			TypePhone typePhone, String email, String password, String confirmPassword) {

		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.cpf = cpf;
		this.ddd = ddd;
		this.phone = phone;
		this.typePhone = typePhone;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public Gender getGender() {
		return gender;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public String getCpf() {
		return cpf;
	}

	public String getDdd() {
		return ddd;
	}

	public String getPhone() {
		return phone;
	}

	public TypePhone getTypePhone() {
		return typePhone;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public Customer createCustomer() {
		return new Customer(new CustomerId(UUID.randomUUID().toString()), new Name(name), gender, new Birth(birth),
				new Cpf(cpf), new Contact(new Phone(ddd, phone, typePhone), new Email(email)), new Password(password),
				new SystemClientStatus(true));
	}
}