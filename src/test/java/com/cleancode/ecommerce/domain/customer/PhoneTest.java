package com.cleancode.ecommerce.domain.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalContactException;

class PhoneTest {

	@Test
	@DisplayName("Deve criar Phone válido com DDD de 2 dígitos e telefone de 8 ou 9 dígitos")
	void shouldCreateValidPhone() {
		Phone phone = new Phone("11", "987654321", TypePhone.MOBILE);

		assertEquals("11", phone.getDdd());
		assertEquals("987654321", phone.getPhone());
		assertEquals(TypePhone.MOBILE, phone.getTypePhone());
		assertEquals("Phone [ddd=11, phone=987654321]", phone.toString());
	}

	@Test
	@DisplayName("Deve lançar exceção se DDD for nulo")
	void shouldThrowExceptionWhenDddIsNull() {
		IllegalContactException exception = assertThrows(IllegalContactException.class,
				() -> new Phone(null, "987654321", TypePhone.MOBILE));
		assertEquals("DDD cannot be null or empty.", exception.getMessage());
	}

	@Test
	@DisplayName("Deve lançar exceção se DDD for vazio")
	void shouldThrowExceptionWhenDddIsEmpty() {
		IllegalContactException exception = assertThrows(IllegalContactException.class,
				() -> new Phone(" ", "987654321", TypePhone.MOBILE));
		assertEquals("DDD cannot be null or empty.", exception.getMessage());
	}

	@Test
	@DisplayName("Deve lançar exceção se telefone for nulo")
	void shouldThrowExceptionWhenPhoneIsNull() {
		IllegalContactException exception = assertThrows(IllegalContactException.class,
				() -> new Phone("11", null, TypePhone.MOBILE));
		assertEquals("Phone cannot be null or empty.", exception.getMessage());
	}

	@Test
	@DisplayName("Deve lançar exceção se telefone for vazio")
	void shouldThrowExceptionWhenPhoneIsEmpty() {
		IllegalContactException exception = assertThrows(IllegalContactException.class,
				() -> new Phone("11", " ", TypePhone.MOBILE));
		assertEquals("Phone cannot be null or empty.", exception.getMessage());
	}

	@Test
	@DisplayName("Deve lançar exceção se DDD tiver mais ou menos que 2 dígitos")
	void shouldThrowExceptionWhenDddIsInvalid() {
		assertThrows(IllegalContactException.class, () -> new Phone("1", "987654321", TypePhone.MOBILE));
		assertThrows(IllegalContactException.class, () -> new Phone("123", "987654321", TypePhone.MOBILE));
	}

	@Test
	@DisplayName("Deve lançar exceção se telefone tiver menos que 8 ou mais que 9 dígitos")
	void shouldThrowExceptionWhenPhoneIsInvalid() {
		assertThrows(IllegalContactException.class, () -> new Phone("11", "1234567", TypePhone.MOBILE));
		assertThrows(IllegalContactException.class, () -> new Phone("11", "1234567890", TypePhone.MOBILE));
	}

	@Test
	@DisplayName("equals e hashCode devem funcionar corretamente")
	void shouldRespectEqualsAndHashCode() {
		Phone phone1 = new Phone("11", "987654321", TypePhone.MOBILE);
		Phone phone2 = new Phone("11", "987654321", TypePhone.MOBILE);
		Phone phone3 = new Phone("21", "12345678", TypePhone.LANDLINE);

		assertEquals(phone1, phone2);
		assertEquals(phone1.hashCode(), phone2.hashCode());
		assertNotEquals(phone1, phone3);
	}
}
