package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.useCase.contract.EncryptPassword;
import com.cleancode.ecommerce.users.domain.user.repository.PasswordEncoderService;

public class EncryptPasswordImpl implements EncryptPassword {

    private final PasswordEncoderService passwordEncoderService;

    public EncryptPasswordImpl(PasswordEncoderService passwordEncoderService) {
		this.passwordEncoderService = passwordEncoderService;
	}
    
    public String execute (String password) {
    	String encodePassword = passwordEncoderService.encode(password);
    	return encodePassword;
    }
}
