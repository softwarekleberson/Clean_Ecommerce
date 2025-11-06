package com.cleancode.ecommerce.adm.domain.user.repository;

public interface PasswordEncoderService {

	String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
