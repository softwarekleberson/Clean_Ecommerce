package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.useCase.DeleteDeliveryImpl;
import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Delivery;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.SystemClientStatus;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteDeliveryImplTest {

	private CustomerRepository repository;
	private DeleteDeliveryImpl deleteDeliveryUseCase;

	@BeforeEach
	void setUp() {
		repository = mock(CustomerRepository.class);
		deleteDeliveryUseCase = new DeleteDeliveryImpl(repository);
	}

	private Customer buildCustomerWithDelivery() {
		Customer customer = new Customer(new com.cleancode.ecommerce.customer.domain.customer.CustomerId("123"),
				new Name("John Doe"),
				Gender.MALE,
				new Birth(LocalDate.of(1990, 1, 1)),
				new Cpf("123.456.789-01"),
				new Contact(
						new Phone("11", "999999999",
							TypePhone.LANDLINE),
						new Email("john@example.com")),
				new Password("password123"),
				new SystemClientStatus(true));

		customer.registerDelivery(new Delivery("d1", "Delivery Phrase", "Receiver", "Street", "123", "Neighborhood",
				"12345678", "Observation", "StreetType", "House", "City", "State", "Country"));

		return customer;
	}

	@Test
	void shouldRemoveDeliverySuccessfully() {
		Customer customer = buildCustomerWithDelivery();
		when(repository.getCustomerById("123")).thenReturn(Optional.of(customer));

		deleteDeliveryUseCase.execute("123", "d1");

		assertEquals(0, customer.getDeliverys().size(), "Delivery should be removed");
		ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
		verify(repository, times(1)).save(captor.capture());
		assertEquals(customer, captor.getValue(), "Updated customer should be saved");
	}

	@Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        when(repository.getCustomerById("999")).thenReturn(Optional.empty());

        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> deleteDeliveryUseCase.execute("999", "d1")
        );

        assertEquals("Customer with id : 999 not found", exception.getMessage());
        verify(repository, never()).save(any());
    }
}
