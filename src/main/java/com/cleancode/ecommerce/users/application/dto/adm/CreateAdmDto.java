package com.cleancode.ecommerce.users.application.dto.adm;

import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.users.domain.adm.Adm;

import jakarta.validation.constraints.NotBlank;

public class CreateAdmDto {

	@NotBlank(message = "The Email not be null or invalid format")
	private String email;
	
	@NotBlank(message = "The Password not be null or invalid format")
	private String password;
	
	public CreateAdmDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public Adm create () {
		return new Adm(new Email(email), new Password(password));
	}
}
