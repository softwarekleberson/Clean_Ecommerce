package com.cleancode.ecommerce.adm.domain.adm;

import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;

public abstract class User {
	
	private Email email;
	private Password password;
	
	public User(Email email, Password password) {
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return this.email.getEmail();
	}
	
	public String getPassword() {
		return password.getPassword();
	}
}
