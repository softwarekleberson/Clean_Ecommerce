package com.cleancode.ecommerce.adm.application;

import com.cleancode.ecommerce.adm.application.contract.CreateNewAdm;
import com.cleancode.ecommerce.adm.application.dto.adm.CreateAdmDto;
import com.cleancode.ecommerce.adm.domain.adm.Adm;
import com.cleancode.ecommerce.adm.domain.adm.repository.AdmRepository;

public class CreateNewAdmImpl implements CreateNewAdm {

	private final AdmRepository repository;
	
	public CreateNewAdmImpl(AdmRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void execute(CreateAdmDto dto) {
		Adm adm = dto.create();
		repository.save(adm);
	}
}
