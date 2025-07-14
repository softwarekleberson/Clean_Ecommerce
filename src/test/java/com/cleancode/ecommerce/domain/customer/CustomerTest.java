package com.cleancode.ecommerce.domain.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Delivery;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

public class CustomerTest {

	private Customer customer;
	private Delivery delivery;
	private Charge charge;

	@BeforeEach
	void setUp() {
		this.customer = new Customer(
			new Id(UUID.randomUUID().toString()),
			new Name("jose"),
			Gender.MALE,
			new Birth(LocalDate.of(1994, 10, 10)),
			new Cpf("478.034.785-40"),
			new Contact(
				new Phone("11", "159741236", TypePhone.MOBILE),
				new Email("josesilva@gmail.com")
			),
			new Password("qIx3N@yqfwrno@sp9ke4")
		);

		this.delivery = new Delivery(UUID.randomUUID().toString(),
			"frase de entrega", "cleiton", "Rua do urubo", "10", "Gavia", "14785-236",
			"Observação", "rua", "casa", "Rio de janeiro", "Rio de janeiro", "Brasil"
		);

		this.charge = new Charge(UUID.randomUUID().toString(),
			"cleiton", "Rua do urubo", "10", "Gavia", "14785-236",
			"Observação", "rua", "casa", "Rio de janeiro", "Rio de janeiro", "Brasil"
		);
	}

	@Test
	void shouldCreateDelivery() {
		customer.insertNewDelivery(delivery);
		assertEquals(1, customer.getDeliverys().size());
	}

	@Test
	void shouldCreateCharge() {
		customer.insertNewCharge(charge);
		assertEquals(1, customer.getCharges().size());
	}

	@Test
	void shouldActivateCustomer() {
		customer.insertNewDelivery(delivery);
		customer.insertNewCharge(charge);
		customer.updateActivationStatus();
		assertEquals(true, customer.isActive());
	}

	@Test
	void shouldDeleteDelivery() {
		customer.insertNewDelivery(delivery);
		Delivery deliveryToRemove = customer.getDeliverys().get(0);
		customer.removeDelivery(deliveryToRemove.getId().toString());
		assertEquals(0, customer.getDeliverys().size());
	}

	@Test
	void shouldDeleteCharge() {
		customer.insertNewCharge(charge);
		Charge chargeToRemove = customer.getCharges().get(0);
		customer.removeCharge(chargeToRemove.getId().toString());
		assertEquals(0, customer.getCharges().size());
	}

	@Test
	void shouldNotAllowDuplicateDelivery() {
		assertThrows(IllegalDomainException.class, () -> {
			customer.insertNewDelivery(delivery);
			customer.insertNewDelivery(delivery);
		});
	}

	@Test
	void shouldNotAllowDuplicateCharge() {
		assertThrows(IllegalDomainException.class, () -> {
			customer.insertNewCharge(charge);
			customer.insertNewCharge(charge);
		});
	}

	@Test
	void shouldGetDeliveryById() {
		customer.insertNewDelivery(delivery);
		Delivery expected = customer.getDeliverys().get(0);
		Delivery actual = customer.getDelivery(expected.getId());
		assertEquals(expected.toString(), actual.toString());
	}

	@Test
	void shouldGetChargeById() {
		customer.insertNewCharge(charge);
		Charge expected = customer.getCharges().get(0);
		Charge actual = customer.getCharge(expected.getId());
		assertEquals(expected.toString(), actual.toString());
	}
}
