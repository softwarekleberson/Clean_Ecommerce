package com.cleancode.ecommerce.customer.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cleancode.ecommerce.customer.domain.customer.repository.PasswordEncoderService;
import com.cleancode.ecommerce.customer.infra.gateways.BCryptPasswordEncoderService;

@Configuration
public class BeanConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoderService passwordEncoderService(BCryptPasswordEncoder encoder) {
        return new BCryptPasswordEncoderService(encoder);
    }
}
