package com.cleancode.ecommerce.domain.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalContactException;

class PhoneTest {

	@Test
	void shouldNotCreateInvalidPhoneDDD() {
	
		assertThrows(IllegalContactException.class, () -> new Phone(null, "123456789", TypePhone.MOBILE));
		assertThrows(IllegalContactException.class, () -> new Phone("", "123456789", TypePhone.MOBILE));
		assertThrows(IllegalContactException.class, () -> new Phone("1", "123456789", TypePhone.MOBILE));
	}
	
	@Test
	void shouldNotCreateInvalidPhone() {
		
		assertThrows(IllegalContactException.class, () -> new Phone("11", null, TypePhone.MOBILE));
		assertThrows(IllegalContactException.class, () -> new Phone("11", "", TypePhone.MOBILE));
		assertThrows(IllegalContactException.class, () -> new Phone("11", "12345678", TypePhone.MOBILE));
	}
	
	@Test
	void shoudCreatePhone() {
		String validDDD = "11";
		String validPhone = "123456789";
		TypePhone validTypePhone = TypePhone.MOBILE;
		
		Phone phone = new Phone(validDDD, validPhone, validTypePhone);
		
		assertEquals(validDDD, phone.getDdd());
		assertEquals(validPhone, phone.getPhone());
		assertEquals(validTypePhone, phone.getTypePhone());
	}
}
