package com.cleancode.ecommerce.adm.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.adm.application.CreateNewAdmImpl;
import com.cleancode.ecommerce.adm.application.contract.CreateNewAdm;
import com.cleancode.ecommerce.adm.domain.adm.repository.AdmRepository;
import com.cleancode.ecommerce.customer.application.useCase.contract.EncryptPassword;

@Configuration
public class AdmConfig {

	@Bean
	public CreateNewAdm createNewAdm (AdmRepository repository, EncryptPassword encryptPassword) {
		return new CreateNewAdmImpl(repository, encryptPassword); 
	}
}
