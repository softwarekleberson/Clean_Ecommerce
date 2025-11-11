package com.cleancode.ecommerce.users.application;

import com.cleancode.ecommerce.customer.application.useCase.contract.EncryptPassword;
import com.cleancode.ecommerce.users.application.contract.CreateNewAdm;
import com.cleancode.ecommerce.users.application.dto.adm.CreateAdmDto;
import com.cleancode.ecommerce.users.domain.adm.Adm;
import com.cleancode.ecommerce.users.domain.adm.repository.AdmRepository;

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
