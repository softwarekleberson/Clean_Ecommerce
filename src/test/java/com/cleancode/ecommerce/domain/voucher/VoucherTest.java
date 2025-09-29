package com.cleancode.ecommerce.domain.voucher;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.adm.domain.voucher.Message;
import com.cleancode.ecommerce.adm.domain.voucher.TypeVoucher;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.adm.domain.voucher.VoucherId;

public class VoucherTest {

	static class TestVoucher extends Voucher {
		public TestVoucher(Message message, TypeVoucher typeVoucher) {
			super(message, typeVoucher);
		}

		public TestVoucher(VoucherId id, Message message, LocalDate emission, TypeVoucher typeVoucher) {
			super(id, message, emission, typeVoucher);
		}
	}

	@Test
	@DisplayName("Deve criar Voucher com ID gerado automaticamente")
	void shouldCreateVoucherWithGeneratedId() {
		Message msg = new Message("Promoção imperdível");
		Voucher voucher = new TestVoucher(msg, TypeVoucher.PROMOTIONAL);

		assertNotNull(voucher.getId());
		assertEquals(msg, voucher.getMessage());
		assertEquals(TypeVoucher.PROMOTIONAL, voucher.getTypeVoucher());
		assertEquals(LocalDate.now(), voucher.getEmission());
	}

	@Test
	@DisplayName("Deve criar Voucher com ID e data definidos")
	void shouldCreateVoucherWithCustomIdAndEmission() {
		VoucherId id = new VoucherId("123");
		LocalDate date = LocalDate.of(2024, 5, 10);
		Message msg = new Message("Cupom especial");

		Voucher voucher = new TestVoucher(id, msg, date, TypeVoucher.PROMOTIONAL);

		assertEquals("123", voucher.getId());
		assertEquals(date, voucher.getEmission());
		assertEquals(msg, voucher.getMessage());
	}

	@Test
	@DisplayName("equals e hashCode devem funcionar corretamente")
	void equalsAndHashCodeShouldWork() {
		VoucherId id = new VoucherId("abc");
		LocalDate date = LocalDate.of(2025, 1, 1);
		Message msg = new Message("Teste");

		Voucher v1 = new TestVoucher(id, msg, date, TypeVoucher.PROMOTIONAL);
		Voucher v2 = new TestVoucher(id, msg, date, TypeVoucher.PROMOTIONAL);

		assertEquals(v1, v2);
		assertEquals(v1.hashCode(), v2.hashCode());
		assertNotEquals(v1, new TestVoucher(new Message("Outro"), TypeVoucher.PROMOTIONAL));
	}

	@Test
	@DisplayName("Message deve lançar exceção se for nula ou vazia")
	void messageShouldValidate() {
		assertThrows(com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException.class,
				() -> new Message(null));
		assertThrows(com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException.class, () -> new Message(""));
	}

	@Test
	@DisplayName("VoucherId deve lançar exceção se string for nula ou vazia")
	void voucherIdShouldValidate() {
		assertThrows(com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException.class,
				() -> new VoucherId(null));
		assertThrows(com.cleancode.ecommerce.adm.domain.adm.exception.IllegalAdmException.class,
				() -> new VoucherId(""));
	}
}
