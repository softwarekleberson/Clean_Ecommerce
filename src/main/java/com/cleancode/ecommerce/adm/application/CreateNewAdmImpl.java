package com.cleancode.ecommerce.adm.application;

import com.cleancode.ecommerce.adm.application.contract.CreateNewAdm;
import com.cleancode.ecommerce.adm.application.dto.adm.CreateAdmDto;
import com.cleancode.ecommerce.adm.domain.adm.Adm;
import com.cleancode.ecommerce.adm.domain.adm.repository.AdmRepository;
import com.cleancode.ecommerce.customer.application.useCase.contract.EncryptPassword;

public class CreateNewAdmImpl implements CreateNewAdm {

	private final AdmRepository repository;
	private final EncryptPassword encryptPassword;
	
	public CreateNewAdmImpl(AdmRepository repository, EncryptPassword encryptPassword) {
		this.repository = repository;
		this.encryptPassword = encryptPassword;
	}
	
	@Override
	public void execute(CreateAdmDto dto) {
		Adm adm = dto.create();
		String passwordEncode = encryptPassword.execute(adm.getPassword());
		adm.updatePassword(passwordEncode);
		repository.save(adm);
	}
}
