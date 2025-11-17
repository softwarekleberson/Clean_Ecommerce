package com.cleancode.ecommerce.users.domain.adm.repository;

import java.util.Optional;

import com.cleancode.ecommerce.users.domain.adm.Adm;

public interface AdmRepository {

	void save (Adm adm);
	Optional<Adm> findByEmail (String email);
}
