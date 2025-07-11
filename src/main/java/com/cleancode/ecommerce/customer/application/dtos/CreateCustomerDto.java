package com.cleancode.ecommerce.customer.application.dtos;

import java.time.LocalDate;

import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

public class CreateCustomerDto {

	private String name;	
	private Gender gender;	
	private LocalDate birth;	
	private String cpf;	
	private String ddd;	
	private String phone;
	private TypePhone typePhone;
	private String email;	
	private String password;
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
	
	public String getEmail() {
		return email;
	}
	
	public Customer createCustomer(Id id, String encryptedPassword) {
		return new Customer(id,
			   new Name(name),
			   gender, 
			   new Birth(birth),
			   new Cpf(cpf),
			   new Contact(new Phone(ddd, phone, typePhone),
			   new Email(email)),
			   new Password(encryptedPassword));
	}
}