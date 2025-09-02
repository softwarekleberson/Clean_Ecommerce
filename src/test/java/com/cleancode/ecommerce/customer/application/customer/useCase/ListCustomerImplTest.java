package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.ListCustomerImpl;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.customer.domain.customer.Password;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListCustomerImplTest {

	private CustomerRepository repository;
	private ListCustomerImpl listCustomerUseCase;

	@BeforeEach
	void setUp() {
		repository = mock(CustomerRepository.class);
		listCustomerUseCase = new ListCustomerImpl(repository);
	}

	private Customer buildCustomer() {
		return new Customer(new CustomerId("123"), new Name("John Doe"), Gender.MALE,
				new Birth(LocalDate.of(1990, 1, 1)), new Cpf("123.456.789-01"),
				new Contact(new Phone("11", "999999999", TypePhone.LANDLINE), new Email("john@example.com")),
				new Password("password123"));
	}

	@Test
	void shouldReturnCustomerDtoWhenCustomerExists() {
		// Arrange
		Customer customer = buildCustomer();
		when(repository.getCustomerById("123")).thenReturn(Optional.of(customer));

		// Act
		ListCustomerDto result = listCustomerUseCase.execute("123");

		// Assert
		assertNotNull(result);
		assertEquals("John Doe", result.name());
		assertEquals("john@example.com", result.email().getEmail());
	}

	@Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        // Arrange
        when(repository.getCustomerById("999")).thenReturn(Optional.empty());

        // Act & Assert
        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> listCustomerUseCase.execute("999")
        );

        assertEquals("Customer with id : 999 not found", exception.getMessage());
    }
}