package com.cleancode.ecommerce.users.infra.gateway;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.users.infra.persistence.adm.AdmEntity;

public interface AdmJpa extends JpaRepository<AdmEntity, String> {

	Optional<AdmEntity> findByEmail(String email);
}
