package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.useCase.DeleteChargeImpl;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteChargeImplTest {

	private CustomerRepository repository;
	private DeleteChargeImpl deleteChargeUseCase;

	@BeforeEach
	void setUp() {
		repository = mock(CustomerRepository.class);
		deleteChargeUseCase = new DeleteChargeImpl(repository);
	}

	private Customer buildCustomerWithCharge() {
		Customer customer = new Customer(new com.cleancode.ecommerce.customer.domain.customer.CustomerId("123"),
				new com.cleancode.ecommerce.shared.kernel.Name("John Doe"),
				com.cleancode.ecommerce.customer.domain.customer.Gender.MALE,
				new com.cleancode.ecommerce.customer.domain.customer.Birth(LocalDate.of(1990, 1, 1)),
				new com.cleancode.ecommerce.shared.kernel.Cpf("123.456.789-01"),
				new com.cleancode.ecommerce.customer.domain.customer.Contact(
						new com.cleancode.ecommerce.customer.domain.customer.Phone("11", "999999999",
								com.cleancode.ecommerce.customer.domain.customer.TypePhone.LANDLINE),
						new com.cleancode.ecommerce.shared.kernel.Email("john@example.com")),
				new com.cleancode.ecommerce.customer.domain.customer.Password("password123"));

		// Add a dummy charge
		customer.registerCharge(new com.cleancode.ecommerce.customer.domain.customer.Charge("c1", "Receiver", "Street",
				"123", "Neighborhood", "12345-678", "Observation", "StreetType", "House", "City", "State", "Country"));

		return customer;
	}

	@Test
	void shouldRemoveChargeSuccessfully() {
		// Arrange
		Customer customer = buildCustomerWithCharge();
		when(repository.getCustomerById("123")).thenReturn(Optional.of(customer));

		// Act
		deleteChargeUseCase.execute("123", "c1");

		// Assert
		assertEquals(0, customer.getCharges().size(), "Charge should be removed");
		ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
		verify(repository, times(1)).save(captor.capture());
		assertEquals(customer, captor.getValue(), "Updated customer should be saved");
	}

	@Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        // Arrange
        when(repository.getCustomerById("999")).thenReturn(Optional.empty());

        // Act & Assert
        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> deleteChargeUseCase.execute("999", "c1")
        );

        assertEquals("Customer with id : 999 not found", exception.getMessage());
        verify(repository, never()).save(any());
    }
}