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
import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.Delivery;
import com.cleancode.ecommerce.customer.domain.customer.Email;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Name;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.shared.domain.Cpf;

public class CustomerTest {

	private Customer customer;
	private Delivery delivery;
	private Charge charge;

	@BeforeEach
	void testCustomer() {
		this.customer = new Customer(new Id(UUID.randomUUID()), new Name("jose"), Gender.MASCULINO,
				new Birth(LocalDate.of(1994, 10, 10)), new Cpf("478.034.785-40"),
				new Contact(new Phone("11", "159741236", TypePhone.CELULAR), new Email("josesilva@gmail.com")),
				new Password("qIx3N@yqfwrno@sp9ke4"));
	}

	@BeforeEach
	void testDelivery() {
		this.delivery = new Delivery("frase de entrega", "cleiton", "Rua do urubo", "10", "Gavia", "14785-236",
				"Observação", "rua", "casa", "Rio de janeiro", "Rio de janeiro", "Brasil");
	}

	@BeforeEach
	void testCharge() {
		this.charge = new Charge("cleiton", "Rua do urubo", "10", "Gavia", "14785-236", "Observação", "rua", "casa",
				"Rio de janeiro", "Rio de janeiro", "Brasil");

	}

	@Test
	void shouldCreateDelivery() {
		this.customer.insertNewDelivery(delivery);
		assertEquals(1, this.customer.getDeliverys().size());
	}

	@Test
	void shouldCreateCharge() {
		this.customer.insertNewCharge(charge);
		assertEquals(1, this.customer.getCharges().size());
	}

	@Test
	void customerActivect() {
		this.customer.insertNewDelivery(delivery);
		this.customer.insertNewCharge(charge);
		this.customer.updateActivationStatus();
		assertEquals(true, this.customer.isActive());
	}

	@Test
	void shoudDeleteDelivery() {
		this.customer.insertNewDelivery(delivery);
		Delivery deliveryRemove = this.customer.getDeliverys().get(0);
		this.customer.removeDelivery(deliveryRemove.getId());

		assertEquals(0, this.customer.getDeliverys().size());
	}

	@Test
	void shoudDeleteCharge() {
		this.customer.insertNewCharge(charge);
		Charge chargeRemove = this.customer.getCharges().get(0);
		this.customer.removeCharge(chargeRemove.getId());

		assertEquals(0, this.customer.getCharges().size());
	}

	@Test
	void ShouldNotCreateDelivery() {
		assertThrows(IllegalDomainException.class, () -> {
			this.customer.insertNewDelivery(delivery);
			this.customer.insertNewDelivery(delivery);
		});
	}
	
	@Test
	void ShouldNotCreateCharge() {
		assertThrows(IllegalDomainException.class, () -> {
			this.customer.insertNewCharge(charge);
			this.customer.insertNewCharge(charge);
		});
	}
	
	@Test
	void shoudGetDelivery() {
		this.customer.insertNewDelivery(delivery);
		Delivery getDelivery = this.customer.getDeliverys().get(0);
		assertEquals(getDelivery.toString(), this.customer.getDelivery(getDelivery.getId()).toString());
	}
	

	@Test
	void shoudGetCharge() {
		this.customer.insertNewCharge(charge);
		Charge getCharge = this.customer.getCharges().get(0);
		assertEquals(getCharge.toString(), this.customer.getCharge(getCharge.getId()).toString());
	}
}
