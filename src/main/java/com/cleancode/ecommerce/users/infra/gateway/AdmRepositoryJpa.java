package com.cleancode.ecommerce.users.infra.gateway;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cleancode.ecommerce.users.domain.adm.Adm;
import com.cleancode.ecommerce.users.domain.adm.repository.AdmRepository;
import com.cleancode.ecommerce.users.infra.mapper.AdmMapper;
import com.cleancode.ecommerce.users.infra.persistence.adm.AdmEntity;

@Repository
public class AdmRepositoryJpa implements AdmRepository {

	private final AdmJpa jpa;

	public AdmRepositoryJpa(AdmJpa jpa) {
		this.jpa = jpa;
	}

	@Override
	@Transactional
	public void save(Adm adm) {
		Optional<AdmEntity> optionalEntity = jpa.findById(adm.getUserId());
		AdmEntity entity;

		if (optionalEntity.isPresent()) {
			entity = AdmMapper.toEntity(adm, optionalEntity.get()); // merge incremental
		} else {
			entity = AdmMapper.toEntity(adm); 
		}

		jpa.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Adm> findByEmail(String email) {
		return jpa.findByEmail(email).map(AdmMapper::toDomain);
	}
}
