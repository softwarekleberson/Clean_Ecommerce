package com.cleancode.ecommerce.adm.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.adm.application.CreateNewAdmImpl;
import com.cleancode.ecommerce.adm.application.contract.CreateNewAdm;
import com.cleancode.ecommerce.adm.domain.adm.repository.AdmRepository;

@Configuration
public class AdmConfig {

	@Bean
	public CreateNewAdm createNewAdm (AdmRepository repository) {
		return new CreateNewAdmImpl(repository); 
	}
}
