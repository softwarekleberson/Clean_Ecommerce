package com.cleancode.ecommerce.customer.domain.customer.repository;

public interface PasswordEncoderService {

	String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
