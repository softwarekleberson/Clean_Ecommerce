package com.cleancode.ecommerce.adm.infra.mapper;

import com.cleancode.ecommerce.adm.domain.adm.Adm;
import com.cleancode.ecommerce.adm.domain.user.UserId;
import com.cleancode.ecommerce.adm.infra.persistence.adm.AdmEntity;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;

public final class AdmMapper {

	private AdmMapper() {
	}

	public static Adm toDomain(AdmEntity entity) {
		if (entity == null)
			return null;

		Adm domain = new Adm(new UserId(entity.getUser_id()), new Email(entity.getEmail()),
				new Password(entity.getPassword()));

		return domain;
	}

	public static AdmEntity toEntity(Adm domain, AdmEntity entity) {
		entity.setEmail(domain.getEmail());
		entity.setPassword(domain.getPassword());
		
		return entity;
	}

	public static AdmEntity toEntity(Adm domain) {
		AdmEntity entity = new AdmEntity();
		entity.setUser_id(domain.getUserId());
		return toEntity(domain, entity);
	}
}
