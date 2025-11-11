package com.cleancode.ecommerce.users.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.customer.application.useCase.contract.EncryptPassword;
import com.cleancode.ecommerce.users.application.CreateNewAdmImpl;
import com.cleancode.ecommerce.users.application.contract.CreateNewAdm;
import com.cleancode.ecommerce.users.domain.adm.repository.AdmRepository;

@Configuration
public class AdmConfig {

	@Bean
	public CreateNewAdm createNewAdm (AdmRepository repository, EncryptPassword encryptPassword) {
		return new CreateNewAdmImpl(repository, encryptPassword); 
	}
}
