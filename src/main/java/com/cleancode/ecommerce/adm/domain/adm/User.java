package com.cleancode.ecommerce.adm.domain.adm;

import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;

public abstract class User {
	
	private final Email email;
	private final Password password;
	
	public User(Email email, Password password) {
		this.email = Objects.requireNonNull(email);
		this.password = Objects.requireNonNull(password);
	}
	
	public String getEmail() {
		return this.email.getEmail();
	}
	
	public String getPassword() {
		return password.getPassword();
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}
}
