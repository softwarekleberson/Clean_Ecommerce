package com.cleancode.ecommerce.customer.infra.gateways;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cleancode.ecommerce.customer.domain.customer.repository.PasswordEncoderService;

@Service
public class BCryptPasswordEncoderService implements PasswordEncoderService {

	private final BCryptPasswordEncoder encoder;

    public BCryptPasswordEncoderService(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}