package com.cleancode.ecommerce.adm.infra.gateway;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cleancode.ecommerce.adm.domain.adm.Adm;
import com.cleancode.ecommerce.adm.domain.adm.repository.AdmRepository;

@Repository
public class AdmRepositoryJpa implements AdmRepository {

	private final AdmJpa jpa;
	
	public AdmRepositoryJpa(AdmJpa jpa) {
		this.jpa = jpa;
	}

	@Override
	public void save(Adm adm) {
		
	}

	@Override
	public Optional<Adm> findAdmByEmail(String email) {
		return Optional.empty();
	}
}
