package com.cleancode.ecommerce.adm.infra.mapper;

import java.util.Map;

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

		Adm domain = new Adm(new UserId(entity.getUser_id()), new Email(entity.getEmail()),
				new Password(entity.getPassword()));

		if (entity.getReplacements() != null) {
			entity.getReplacements().values().forEach(e -> {
				domain.addVoucher(ReplacementMapper.toDomain(e));
			});
		}

		return domain;
	}

	// Entity <- Domain (merge incremental)
	public static AdmEntity toEntity(Adm domain, AdmEntity entity) {
		entity.setEmail(domain.getEmail());
		entity.setPassword(domain.getPassword());

		Map<String, ReplacementEntity> existing = entity.getReplacements();
		Map<String, Voucher> domainVouchers = domain.getAllVouchers();

		// Remove vouchers que não existem mais
		existing.keySet().removeIf(id -> !domainVouchers.containsKey(id));

		// Atualiza ou adiciona novos
		for (Map.Entry<String, Voucher> entry : domainVouchers.entrySet()) {
			String id = entry.getKey();
			Voucher v = entry.getValue();

			if (v instanceof Replacement replacement) {
				existing.compute(id, (key, oldEntity) -> {
					if (oldEntity != null) {
						ReplacementMapper.updateEntity(replacement, oldEntity);
						return oldEntity;
					} else {
						ReplacementEntity newEntity = ReplacementMapper.toEntity(replacement);
						newEntity.setAdm(entity);
						return newEntity;
					}
				});
			}
		}

		return entity;
	}

	// Criação de nova entidade (quando não existe)
	public static AdmEntity toEntity(Adm domain) {
		AdmEntity entity = new AdmEntity();
		entity.setUser_id(domain.getUserId());
		return toEntity(domain, entity);
	}
}
