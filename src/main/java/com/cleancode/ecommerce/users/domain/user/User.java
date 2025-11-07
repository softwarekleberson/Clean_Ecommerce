package com.cleancode.ecommerce.users.domain.user;

import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;

public abstract class User {
	
	private final UserId userId;
	private final Email email;
	private Password password;
	
	public User(Email email, Password password) {
		this.userId = new UserId();
		this.email = Objects.requireNonNull(email);
		this.password = Objects.requireNonNull(password);
	}
	
	public User(UserId userId, Email email, Password password) {
		this.userId = Objects.requireNonNull(userId);
		this.email = Objects.requireNonNull(email);
		this.password = Objects.requireNonNull(password);
	}
	
	public void updatePassword (String passwordEncode) {
		this.password = new Password(passwordEncode);
	}
	
	public String getUserId() {
		return userId.getUserId();
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
