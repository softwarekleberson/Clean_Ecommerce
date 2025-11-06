package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.useCase.contract.EncryptPassword;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.PasswordEncoderService;

public class EncryptPasswordImpl implements EncryptPassword {

    private final PasswordEncoderService passwordEncoderService;

    public EncryptPasswordImpl(PasswordEncoderService passwordEncoderService) {
		this.passwordEncoderService = passwordEncoderService;
	}
    
    public void execute (Customer customer) {
    	String encodePassword = passwordEncoderService.encode(customer.getPassword().getPassword());
    	customer.updatePassword(encodePassword);
    }
}
