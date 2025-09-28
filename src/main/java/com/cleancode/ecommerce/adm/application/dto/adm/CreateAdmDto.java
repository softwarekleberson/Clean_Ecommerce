package com.cleancode.ecommerce.adm.application.dto.adm;

import com.cleancode.ecommerce.adm.domain.adm.Adm;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;

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
	
	public Adm createAdm () {
		return new Adm(new Email(email), new Password(password));
	}
}
