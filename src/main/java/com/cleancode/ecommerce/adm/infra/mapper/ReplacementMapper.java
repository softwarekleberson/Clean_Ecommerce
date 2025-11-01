package com.cleancode.ecommerce.adm.infra.mapper;

import com.cleancode.ecommerce.adm.domain.voucher.Discount;
import com.cleancode.ecommerce.adm.domain.voucher.Message;
import com.cleancode.ecommerce.adm.domain.voucher.Replacement;
import com.cleancode.ecommerce.adm.domain.voucher.TypeVoucher;
import com.cleancode.ecommerce.adm.domain.voucher.VoucherId;
import com.cleancode.ecommerce.adm.infra.persistence.voucher.ReplacementEntity;
import com.cleancode.ecommerce.adm.infra.persistence.voucher.TypeVoucherEntity;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;

public final class ReplacementMapper {

	private ReplacementMapper() {
	}

	public static ReplacementEntity toEntity(Replacement replacement) {
		if (replacement == null)
			return null;

		ReplacementEntity entity = new ReplacementEntity();
		entity.setVoucherId(replacement.getId());
		entity.setMessage(replacement.getMessage().getMessage());
		entity.setEmission(replacement.getEmission());
		entity.setTypeVoucher(TypeVoucherEntity.valueOf(replacement.getTypeVoucher().name()));
		entity.setCustomerId(replacement.getCustomerId().getValue());
		entity.setDiscount(replacement.getDiscount().getDiscount());

		return entity;
	}

	public static void updateEntity(Replacement replacement, ReplacementEntity entity) {
		if (replacement == null || entity == null)
			return;

		entity.setMessage(replacement.getMessage().getMessage());
		entity.setEmission(replacement.getEmission());
		entity.setTypeVoucher(TypeVoucherEntity.valueOf(replacement.getTypeVoucher().name()));
		entity.setCustomerId(replacement.getCustomerId().getValue());
		entity.setDiscount(replacement.getDiscount().getDiscount());
	}

	public static Replacement toDomain(ReplacementEntity entity) {
		if (entity == null)
			return null;

		VoucherId voucherId = new VoucherId(entity.getVoucherId());
		Message message = new Message(entity.getMessage());
		CustomerId customerId = new CustomerId(entity.getCustomerId());
		Discount discount = new Discount(entity.getDiscount());

		return new Replacement(voucherId, message, entity.getEmission(),
				TypeVoucher.valueOf(entity.getTypeVoucher().name()), customerId, discount);
	}
}
