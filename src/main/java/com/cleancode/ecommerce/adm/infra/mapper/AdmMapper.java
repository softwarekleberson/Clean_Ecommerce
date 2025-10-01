package com.cleancode.ecommerce.adm.infra.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.adm.domain.adm.Adm;
import com.cleancode.ecommerce.adm.domain.adm.UserId;
import com.cleancode.ecommerce.adm.domain.voucher.Replacement;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.adm.infra.persistence.adm.AdmEntity;
import com.cleancode.ecommerce.adm.infra.persistence.voucher.ReplacementEntity;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.shared.kernel.Email;

public final class AdmMapper {

	private AdmMapper() {
	}

	// Domain <- Entity
	public static Adm toDomain(AdmEntity entity) {
		if (entity == null)
			return null;

		Adm domain = new Adm(new UserId(entity.getUser_id()), new Email(entity.getEmail()), new Password(entity.getPassword()));

		if (entity.getReplacements() != null) {
			Map<String, Voucher> vouchers = entity.getReplacements().entrySet().stream()
					.collect(Collectors.toMap(Map.Entry::getKey, e -> ReplacementMapper.toDomain(e.getValue())));

			vouchers.values().forEach(domain::addVoucher);
		}

		return domain;
	}

	// Entity <- Domain
	public static AdmEntity toEntity(Adm domain) {
		if (domain == null) return null;

	    AdmEntity entity = new AdmEntity();

	    if (domain.getUserId() != null) {
	        entity.setUser_id(domain.getUserId());
	    }
	    
		entity.setEmail(domain.getEmail());
		entity.setPassword(domain.getPassword());

		Map<String, ReplacementEntity> replacements = new HashMap<>();

		if (domain.getAllVouchers() != null) {
			for (Map.Entry<String, Voucher> entry : domain.getAllVouchers().entrySet()) {
				Voucher voucher = entry.getValue();
				if (voucher instanceof Replacement replacement) {
					ReplacementEntity entityVoucher = ReplacementMapper.toEntity(replacement);
					entityVoucher.setAdm(entity); // garante referência ao AdmEntity
					replacements.put(entry.getKey(), entityVoucher);
				}
			}
		}

		entity.setReplacements(replacements);
		return entity;
	}
}
