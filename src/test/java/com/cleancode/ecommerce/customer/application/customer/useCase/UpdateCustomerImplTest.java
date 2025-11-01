package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.UpdateCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.UpdateCustomerImpl;
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

class UpdateCustomerImplTest {

    private CustomerRepository repository;
    private UpdateCustomerImpl updateCustomerUseCase;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        updateCustomerUseCase = new UpdateCustomerImpl(repository);
    }

    private Customer buildCustomer() {
        return new Customer(
                new CustomerId("123"),
                new Name("John Doe"),
                Gender.MALE,
                new Birth(LocalDate.of(1990, 1, 1)),
                new Cpf("123.456.789-01"),
                new Contact(new Phone("11", "999999999", TypePhone.LANDLINE), new Email("john@example.com")),
                new Password("password123"),
                new SystemClientStatus(true)
        );
    }

    @Test
    void shouldUpdateCustomerSuccessfully() {
        Customer customer = buildCustomer();
        when(repository.getCustomerById("123")).thenReturn(java.util.Optional.of(customer));

        UpdateCustomerDto dto = mock(UpdateCustomerDto.class);
        when(dto.name()).thenReturn("Jane Doe");
        when(dto.birth()).thenReturn(LocalDate.of(1995, 5, 15));
        when(dto.ddd()).thenReturn("21");
        when(dto.phone()).thenReturn("888888888");
        when(dto.typePhone()).thenReturn(TypePhone.LANDLINE);

        ListCustomerDto result = updateCustomerUseCase.execute("123", dto);

        assertEquals("Jane Doe", customer.getName().getName());
        assertEquals(LocalDate.of(1995, 5, 15), customer.getBirth().getBirth());
        assertEquals("21", customer.getContact().getDDD());
        assertEquals("888888888", customer.getContact().getPhone());

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository, times(1)).save(captor.capture());
        assertEquals(customer, captor.getValue());

        assertNotNull(result);
        assertEquals(customer.getName().getName(), result.name());
    }

    @Test
    void shouldThrowWhenCustomerNotFound() {
        when(repository.getCustomerById("999")).thenReturn(java.util.Optional.empty());

        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> updateCustomerUseCase.execute("999", mock(UpdateCustomerDto.class))
        );

        assertEquals("Customer with id : 999 not found", exception.getMessage());
        verify(repository, never()).save(any());
    }
}