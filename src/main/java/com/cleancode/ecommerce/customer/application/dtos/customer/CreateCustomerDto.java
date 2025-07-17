package com.cleancode.ecommerce.customer.application.dtos.customer;

import java.time.LocalDate;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCustomerDto {

	@NotBlank
	private String name;
	
	@NotNull
	private Gender gender;
	
	@NotNull
	private LocalDate birth;
	
	@NotBlank
	private String cpf;	
	
	@NotBlank
	private String ddd;	
	
	@NotBlank
	private String phone;
	
	@NotNull
	private TypePhone typePhone;
	
	@NotBlank
	private String email;	
	
	@NotBlank
	private String password;
	
	@NotBlank
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
	
	public String getPassword() {
		return password;
	}
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	
	public Customer createCustomer() {
		return new Customer(
			   new Id(UUID.randomUUID().toString()),
			   new Name(name),
			   gender, 
			   new Birth(birth),
			   new Cpf(cpf),
			   new Contact(new Phone(ddd, phone, typePhone),
			   new Email(email)),
			   new Password(password));
	}
}