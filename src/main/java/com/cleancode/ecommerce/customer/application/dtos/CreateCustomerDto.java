package com.cleancode.ecommerce.customer.application.dtos;

import java.time.LocalDate;

import com.cleancode.ecommerce.customer.domain.Birth;
import com.cleancode.ecommerce.customer.domain.Contact;
import com.cleancode.ecommerce.customer.domain.Customer;
import com.cleancode.ecommerce.customer.domain.CustomerId;
import com.cleancode.ecommerce.customer.domain.Email;
import com.cleancode.ecommerce.customer.domain.Gender;
import com.cleancode.ecommerce.customer.domain.Name;
import com.cleancode.ecommerce.customer.domain.Password;
import com.cleancode.ecommerce.customer.domain.Phone;
import com.cleancode.ecommerce.customer.shared.domain.Cpf;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateCustomerDto {

	@NotBlank(message = "Name is requered")
	private String name;
	
	@NotNull(message = "Gender is requerid")
	private Gender gender;
	
	@NotNull(message = "Birth is requerid")
	private LocalDate birth;
	
	@NotBlank(message = "Cpf is requerid")
	private String cpf;
	
	@NotBlank(message = "DDD is requerid")
	private String ddd;
	
	@NotBlank(message = "Phone is requerid")
	private String phone;
	
	@NotBlank(message = "Email is requerid")
	private String email;
	
	@NotBlank(message = "Password is requerid")
	private String password;
	
	@NotBlank(message = "Confirm Password")
	private String confirmPassword;
	
	public CreateCustomerDto(String name, Gender gender, LocalDate birth, String cpf, String ddd, String phone,
			String email, String password, String confirmPassword) {
		
		this.name = name;
		this.gender = gender;
		this.birth = birth;
		this.cpf = cpf;
		this.ddd = ddd;
		this.phone = phone;
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
	
	public String getEmail() {
		return email;
	}
	
	public Customer createCustomer(CustomerId id, String encryptedPassword) {
		return new Customer(id,
			   new Name(name),
			   gender, 
			   new Birth(birth),
			   new Cpf(cpf),
			   new Contact(new Phone(ddd, phone),
			   new Email(email)),
			   new Password(encryptedPassword));
	}
}