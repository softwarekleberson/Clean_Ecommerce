package com.cleancode.ecommerce.adm.domain.adm.repository;

import java.util.Optional;

import com.cleancode.ecommerce.adm.domain.adm.Adm;

public interface AdmRepository {

	void save (Adm adm);
	Optional<Adm> findAdmByEmail (String email);
}
