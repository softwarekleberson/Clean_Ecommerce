package com.cleancode.ecommerce.adm.infra.gateway;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.adm.infra.persistence.adm.AdmEntity;

public interface AdmJpa extends JpaRepository<AdmEntity, Long>{

}
