package com.cleancode.ecommerce.users.infra.mapper;

import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.users.domain.adm.Adm;
import com.cleancode.ecommerce.users.domain.user.UserId;
import com.cleancode.ecommerce.users.infra.persistence.adm.AdmEntity;

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
