package com.cleancode.ecommerce.customer.domain.customer.repository;

public interface PasswordEncryptorRepository {

	String encryptPassword(String password);
	boolean validateEncryptedPassword (String password, String encryptedPassword);
}
