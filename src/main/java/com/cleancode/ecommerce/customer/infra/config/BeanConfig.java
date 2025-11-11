package com.cleancode.ecommerce.customer.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cleancode.ecommerce.customer.infra.gateways.BCryptPasswordEncoderService;
import com.cleancode.ecommerce.users.domain.user.repository.PasswordEncoderService;

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
