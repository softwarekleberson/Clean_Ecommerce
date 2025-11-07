package com.cleancode.ecommerce.users.domain.user.repository;

public interface PasswordEncoderService {

	String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
