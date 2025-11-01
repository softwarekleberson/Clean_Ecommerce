package com.cleancode.ecommerce.domain.customer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.card.Card;
import com.cleancode.ecommerce.customer.domain.card.Code;
import com.cleancode.ecommerce.customer.domain.card.ExpirationDate;
import com.cleancode.ecommerce.customer.domain.card.Flag;
import com.cleancode.ecommerce.customer.domain.card.NumberCard;
import com.cleancode.ecommerce.customer.domain.card.PrintedName;
import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.Delivery;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.SystemClientStatus;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

public class CustomerTest {

	private Customer customer;

	@BeforeEach
	void setUp() {
		customer = new Customer(new CustomerId("123"), new Name("Kleberson"), Gender.MALE,
				new Birth(LocalDate.of(1995, 5, 20)), new Cpf("123.456.789-09"),
				new Contact(new Phone("11", "999999999", TypePhone.MOBILE), new Email("teste@email.com")),
				new Password("123456"), new SystemClientStatus(true));
	}

	// ---------- ID e Update ----------
	@Test
	void shouldAssignId() {
		customer.assignId("456");
		assertThat(customer.getId().getValue()).isEqualTo("456");
	}

	@Test
	void shouldUpdateCustomerFields() {
		customer.updateCustomer("Novo Nome", LocalDate.of(2000, 1, 1), "21", "988888888", TypePhone.MOBILE);

		assertThat(customer.getName().getName()).isEqualTo("Novo Nome");
		assertThat(customer.getBirth().getBirth()).isEqualTo(LocalDate.of(2000, 1, 1));
		assertThat(customer.getContact().getDDD()).isEqualTo("21");
		assertThat(customer.getContact().getPhone()).isEqualTo("988888888");
		assertThat(customer.getContact().getTypePhone()).isEqualTo(TypePhone.MOBILE);
	}

	// ---------- Password ----------
	@Test
	void shouldUpdatePasswordWhenValid() {
		customer.updatePassword("novaSenha");
		assertThat(customer.getPassword().getPassword()).isEqualTo("novaSenha");
	}

	@Test
	void shouldNotUpdatePasswordWhenBlank() {
		Password old = customer.getPassword();
		customer.updatePassword(" ");
		assertThat(customer.getPassword()).isEqualTo(old);
	}

	// ---------- SystemClientStatus ----------
	@Test
	void shouldChangeActivationStatusByAdmin() {
		boolean oldStatus = customer.getSystemClientStatus();
		customer.changeActivationStatusByAdmin();
		assertThat(customer.getSystemClientStatus()).isNotEqualTo(oldStatus);
	}

	// ---------- Activation ----------
	@Test
	void shouldActivateWhenHasChargesAndDeliveries() {
		customer.registerCharge(buildCharge("c1"));
		customer.registerDelivery(buildDelivery("d1"));

		boolean active = customer.checkActivationRequirements();
		assertThat(active).isTrue();
		assertThat(customer.isActive()).isTrue();
	}

	@Test
	void shouldNotActivateWhenNoChargesOrDeliveries() {
		boolean active = customer.checkActivationRequirements();
		assertThat(active).isFalse();
	}

	// ---------- Cards ----------
	@Test
	void deveAdicionarUmCartaoNaoPrincipal() {
		Card card = buildCard(false, "6011145842545060");

		customer.registerCard(card);

		assertThat(customer.getCards()).hasSize(1);
		assertThat(customer.getCards().get(0).isMain()).isFalse();
	}

	@Test
	void deveAdicionarCartaoPrincipal() {
		Card card = buildCard(true, "6011145842545060");

		customer.registerCard(card);

		assertThat(customer.getCards()).hasSize(1);
		assertThat(customer.getCards().get(0).isMain()).isTrue();
	}

	@Test
	void deveGarantirApenasUmCartaoPrincipal() {
		Card card1 = buildCard(true, "6011145842545060");
		Card card2 = buildCard(true, "5153835260752251");

		customer.registerCard(card1);
		customer.registerCard(card2);

		assertThat(customer.getCards()).hasSize(2);

		long mainCards = customer.getCards().stream().filter(Card::isMain).count();
		assertThat(mainCards).isEqualTo(1);

		assertThat(customer.getCards().get(1).isMain()).isTrue();
	}

	@Test
	void devePermitirVariosCartoesNaoPrincipais() {
		Card card1 = buildCard(false, "4389355312193718");
		Card card2 = buildCard(false, "5153835260752251");

		customer.registerCard(card1);
		customer.registerCard(card2);

		assertThat(customer.getCards()).hasSize(2);
		assertThat(customer.getCards().stream().allMatch(c -> !c.isMain())).isTrue();
	}

	// ---------- Deliveries ----------
	@Test
	void shouldFindDeliveryById() {
		Delivery delivery = buildDelivery("d123");
		customer.registerDelivery(delivery);

		Delivery found = customer.findDeliveryById(delivery.getPublicId());
		assertThat(found).isEqualTo(delivery);
	}

	@Test
	void shouldThrowWhenDeliveryNotFound() {
		assertThatThrownBy(() -> customer.findDeliveryById("inexistente")).isInstanceOf(IllegalDomainException.class)
				.hasMessageContaining("Id Delivery not found");
	}

	@Test
	void shouldRemoveDeliveryById() {
		Delivery delivery = buildDelivery("d1");
		customer.registerDelivery(delivery);

		customer.removeDeliveryById(delivery.getPublicId());
		assertThat(customer.getDeliverys()).isEmpty();
	}

	// ---------- Charges ----------
	@Test
	void shouldFindChargeById() {
		Charge charge = buildCharge("c123");
		customer.registerCharge(charge);

		Charge found = customer.findChargeById(charge.getPublicId());
		assertThat(found).isEqualTo(charge);
	}

	@Test
	void shouldThrowWhenChargeNotFound() {
		assertThatThrownBy(() -> customer.findChargeById("inexistente")).isInstanceOf(IllegalDomainException.class)
				.hasMessageContaining("Id Charge not found");
	}

	@Test
	void shouldRemoveChargeById() {
		Charge charge = buildCharge("c1");
		customer.registerCharge(charge);

		customer.removeChargeById(charge.getPublicId());
		assertThat(customer.getCharges()).isEmpty();
	}

	// ---------- Builders auxiliares ----------
	private Delivery buildDelivery(String id) {
		return new Delivery(id, true, "Frase", "João", "Rua A", "10", "Bairro", "12345678", "Obs", "Rua", "Casa", "Cidade",
				"SP", "Brasil");
	}

	private Charge buildCharge(String id) {
		return new Charge(id, true, "Maria", "Rua B", "20", "Centro", "87654321", "Obs", "Avenida", "Apartamento", "Cidade",
				"RJ", "Brasil");
	}

	private Card buildCard(boolean main, String number) {
		return new Card(main, new PrintedName("JOAO DA SILVA"), new Code("123"), new NumberCard(number),
				new ExpirationDate(LocalDate.of(2030, 12, 31)), Flag.VISA);
	}
}
