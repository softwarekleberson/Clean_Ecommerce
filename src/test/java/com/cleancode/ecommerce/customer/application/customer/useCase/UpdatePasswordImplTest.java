package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.UpdatePasswordDto;
import com.cleancode.ecommerce.customer.application.useCase.UpdatePasswordImpl;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.customer.domain.customer.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdatePasswordImplTest {

	private CustomerRepository repository;
	private UpdatePasswordImpl updatePasswordUseCase;

	@BeforeEach
	void setUp() {
		repository = mock(CustomerRepository.class);
		updatePasswordUseCase = new UpdatePasswordImpl(repository);
	}

	private Customer buildCustomer() {
		return new Customer(new CustomerId("123"), new Name("John Doe"), Gender.MALE,
				new Birth(LocalDate.of(1990, 1, 1)), new Cpf("123.456.789-01"),
				new Contact(new Phone("11", "999999999", TypePhone.LANDLINE), new Email("john@example.com")),
				new Password("OldPassword123!"));
	}

	@Test
	void shouldUpdatePasswordSuccessfully() {
		// Arrange
		Customer customer = buildCustomer();
		when(repository.getCustomerById("123")).thenReturn(java.util.Optional.of(customer));

		UpdatePasswordDto dto = mock(UpdatePasswordDto.class);
		when(dto.password()).thenReturn("NewPassword123!");
		when(dto.confirmPassword()).thenReturn("NewPassword123!");

		// Act
		updatePasswordUseCase.execute("123", dto);

		// Assert
		assertEquals("NewPassword123!", customer.getPassword().getPassword());

		ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
		verify(repository, times(1)).save(captor.capture());
		assertEquals(customer, captor.getValue());
	}

	@Test
    void shouldThrowWhenCustomerNotFound() {
        // Arrange
        when(repository.getCustomerById("999")).thenReturn(java.util.Optional.empty());

        // Act & Assert
        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> updatePasswordUseCase.execute("999", mock(UpdatePasswordDto.class))
        );

        assertEquals("Customer with id : 999 not found", exception.getMessage());
        verify(repository, never()).save(any());
    }

	@Test
	void shouldThrowWhenPasswordAndConfirmDoNotMatch() {
		// Arrange
		Customer customer = buildCustomer();
		when(repository.getCustomerById("123")).thenReturn(java.util.Optional.of(customer));

		UpdatePasswordDto dto = mock(UpdatePasswordDto.class);
		when(dto.password()).thenReturn("NewPassword123!");
		when(dto.confirmPassword()).thenReturn("MismatchPassword123!");

		// Act & Assert
		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> updatePasswordUseCase.execute("123", dto));

		assertEquals("password confirmation is different from password", exception.getMessage());
		verify(repository, never()).save(any());
	}
}