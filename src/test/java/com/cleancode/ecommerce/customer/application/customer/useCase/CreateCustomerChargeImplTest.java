package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.dtos.address.CreateChargeDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerChargeImpl;
import com.cleancode.ecommerce.customer.domain.customer.*;
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

class CreateCustomerChargeImplTest {

	private CustomerRepository repository;
	private CreateCustomerChargeImpl useCase;

	@BeforeEach
	void setUp() {
		repository = mock(CustomerRepository.class);
		useCase = new CreateCustomerChargeImpl(repository);
	}

	private Customer buildCustomer() {
		return new Customer(new CustomerId("123"), new Name("João Silva"), Gender.MALE,
				new Birth(LocalDate.of(1990, 1, 1)), new Cpf("438.287.059-50"),
				new Contact(new Phone("11", "999999999", TypePhone.MOBILE), new Email("joao@email.com")),
				new Password("senha123"),
				new SystemClientStatus(true));
	}

	@Test
	void mustCreateChargeWhenCustomerExists() {
		Customer customer = buildCustomer();
		when(repository.getCustomerById("123")).thenReturn(Optional.of(customer));

		CreateChargeDto dto = mock(CreateChargeDto.class);
		Charge charge = new Charge("c1", "Maria", "Rua X", "123", "Centro", "12345678", "Obs", "Rua", "Casa",
				"São Paulo", "SP", "Brasil");
		when(dto.createCharge()).thenReturn(charge);

		ListCustomerDto result = useCase.execute("123", dto);

		assertNotNull(result);
		assertEquals("João Silva", result.name());
		assertEquals(1, customer.getCharges().size());
		assertEquals(charge, customer.getCharges().get(0));

		ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
		verify(repository, times(1)).save(captor.capture());
		assertEquals(1, captor.getValue().getCharges().size());
	}

	@Test
    void mustThrowExceptionWhenClientDoesNotExist() {
        when(repository.getCustomerById("999")).thenReturn(Optional.empty());
        CreateChargeDto dto = mock(CreateChargeDto.class);

        IllegalDomainException ex = assertThrows(
                IllegalDomainException.class,
                () -> useCase.execute("999", dto)
        );

        assertEquals("Customer with id : 999 not found", ex.getMessage());
        verify(repository, never()).save(any());
    }
}
