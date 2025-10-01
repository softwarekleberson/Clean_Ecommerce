package com.cleancode.ecommerce.domain.voucher;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.adm.domain.voucher.Discount;
import com.cleancode.ecommerce.adm.domain.voucher.Message;
import com.cleancode.ecommerce.adm.domain.voucher.Replacement;
import com.cleancode.ecommerce.adm.domain.voucher.TypeVoucher;
import com.cleancode.ecommerce.adm.domain.voucher.VoucherId;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;

class ReplacementTest {

	@Test
	void shouldCreateReplacementCorrectly() {
		// Arrange
		VoucherId id = new VoucherId();
		Message message = new Message("Cupom de teste");
		LocalDate emission = LocalDate.of(2025, 9, 30);
		TypeVoucher typeVoucher = TypeVoucher.REPLACEMENT;
		CustomerId customerId = new CustomerId("123e4567-e89b-12d3-a456-426614174000");
		Discount discount = new Discount(15);

		Replacement replacement = new Replacement(id, message, emission, typeVoucher, customerId, discount);

		assertNotNull(replacement);
		assertEquals(id.getVoucherId(), replacement.getId());
		assertEquals(message, replacement.getMessage());
		assertEquals(emission, replacement.getEmission());
		assertEquals(typeVoucher, replacement.getTypeVoucher());
		assertEquals(customerId, replacement.getCustomerId());
		assertEquals(discount, replacement.getDiscount());
	}

	@Test
	void shouldConsiderEqualWhenIdsAreSame() {
		VoucherId id = new VoucherId();
		Message message = new Message("Cupom A");
		LocalDate emission = LocalDate.now();
		TypeVoucher typeVoucher = TypeVoucher.REPLACEMENT;
		CustomerId customerId = new CustomerId("123e4567-e89b-12d3-a456-426614174000");
		Discount discount = new Discount(20);

		Replacement r1 = new Replacement(id, message, emission, typeVoucher, customerId, discount);
		Replacement r2 = new Replacement(id, new Message("Outro cupom"), emission, typeVoucher, customerId, discount);

		assertEquals(r1, r2);
		assertEquals(r1.hashCode(), r2.hashCode());
	}

	@Test
	void shouldNotBeEqualWhenIdsAreDifferent() {
		Replacement r1 = new Replacement(new VoucherId(), new Message("Cupom 1"), LocalDate.now(),
				TypeVoucher.REPLACEMENT, new CustomerId("c1"), new Discount(10));

		Replacement r2 = new Replacement(new VoucherId(), new Message("Cupom 2"), LocalDate.now(),
				TypeVoucher.REPLACEMENT, new CustomerId("c2"), new Discount(20));

		assertNotEquals(r1, r2);
	}
}
