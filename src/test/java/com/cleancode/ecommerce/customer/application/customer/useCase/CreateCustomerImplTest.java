package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerImpl;
import com.cleancode.ecommerce.customer.application.useCase.EncryptPasswordImpl;
import com.cleancode.ecommerce.customer.application.useCase.contract.PasswordValidationCheck;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.SystemClientStatus;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.event.EventPublisher;
import com.cleancode.ecommerce.event.NewCustomerEvent;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCustomerImplTest {

	private CustomerRepository repository;
	private PasswordValidationCheck passwordValidation;
	private EventPublisher eventPublisher;
	private CreateCustomerImpl useCase;
    private EncryptPasswordImpl encryptPassword;

	@BeforeEach
	void setUp() {
		repository = mock(CustomerRepository.class);
		passwordValidation = mock(PasswordValidationCheck.class);
		eventPublisher = mock(EventPublisher.class);
		useCase = new CreateCustomerImpl(repository, passwordValidation, eventPublisher, encryptPassword);
	}

	@Test
	void deveCriarClienteComSucesso() {
		// Arrange
		CreateCustomerDto dto = mock(CreateCustomerDto.class);
		Customer customer = new Customer(new CustomerId("123"),
				new Name("João Silva"), Gender.MALE,
				new Birth(LocalDate.of(1990, 1, 1)), new Cpf("123.456.789-01"),
				new Contact(new Phone("11", "999999999", TypePhone.LANDLINE),
						new Email("joao@email.com")),
				new Password("senha123"),
				new SystemClientStatus(true));

		when(dto.getPassword()).thenReturn("senha123");
		when(dto.getConfirmPassword()).thenReturn("senha123");
		when(dto.createCustomer()).thenReturn(customer);

		// Act
		ListCustomerDto result = useCase.execute(dto);

		// Assert
		assertNotNull(result);
		assertEquals("João Silva", result.name());

		verify(passwordValidation).passwordCheckAndConfirmPassword("senha123", "senha123");
		verify(passwordValidation).validateAcceptablePasswordFormat("senha123");
		verify(repository).save(customer);

		ArgumentCaptor<NewCustomerEvent> eventCaptor = ArgumentCaptor.forClass(NewCustomerEvent.class);
		verify(eventPublisher).publish(eventCaptor.capture());

		NewCustomerEvent publishedEvent = eventCaptor.getValue();
		assertEquals("João Silva", publishedEvent.getName());
		assertEquals("joao@email.com", publishedEvent.getEmail());
	}

	@Test
	void deveLancarExcecaoQuandoSenhaNaoConfere() {
		// Arrange
		CreateCustomerDto dto = mock(CreateCustomerDto.class);
		when(dto.getPassword()).thenReturn("senha123");
		when(dto.getConfirmPassword()).thenReturn("senhaErrada");

		doThrow(new IllegalArgumentException("Senhas não conferem")).when(passwordValidation)
				.passwordCheckAndConfirmPassword("senha123", "senhaErrada");

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> useCase.execute(dto));

		verify(repository, never()).save(any());
		verify(eventPublisher, never()).publish(any());
	}

	@Test
	void deveLancarExcecaoQuandoFormatoSenhaInvalido() {
		// Arrange
		CreateCustomerDto dto = mock(CreateCustomerDto.class);
		when(dto.getPassword()).thenReturn("123");
		when(dto.getConfirmPassword()).thenReturn("123");

		doNothing().when(passwordValidation).passwordCheckAndConfirmPassword("123", "123");
		doThrow(new IllegalArgumentException("Formato de senha inválido")).when(passwordValidation)
				.validateAcceptablePasswordFormat("123");

		// Act & Assert
		assertThrows(IllegalArgumentException.class, () -> useCase.execute(dto));

		verify(repository, never()).save(any());
		verify(eventPublisher, never()).publish(any());
	}
}