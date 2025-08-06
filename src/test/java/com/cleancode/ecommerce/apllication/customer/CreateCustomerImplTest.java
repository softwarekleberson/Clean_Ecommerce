package com.cleancode.ecommerce.apllication.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.application.dtos.customer.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerImpl;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class CreateCustomerImplTest {

    private CustomerRepository repository;
    private CreateCustomerImpl createCustomer;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);

        createCustomer = new CreateCustomerImpl(repository);
    }

    @Test
    void mustCreateCustomerSuccessfully() {
        CreateCustomerDto dto = new CreateCustomerDto(
                "Kleberson",
                Gender.MALE,
                LocalDate.of(1999, 1, 1),
                "123.456.789-01",
                "11",
                "987654321",
                TypePhone.MOBILE,
                "kleberson@example.com",
                "senha123",
                "senha123"
        );

        createCustomer.execute(dto);

        verify(repository, times(1)).save(any(Customer.class));
    }

    @Test
    void shouldThrowExceptionWhenPasswordDoesNotMatch() {
        CreateCustomerDto dto = new CreateCustomerDto(
                "Kleberson",
                Gender.MALE,
                LocalDate.of(1999, 1, 1),
                "12345678901",
                "11",
                "987654321",
                TypePhone.MOBILE,
                "kleberson@example.com",
                "senha123",
                "outraSenha"
        );

        IllegalDomainException exception = assertThrows(IllegalDomainException.class, () -> {
            createCustomer.execute(dto);
        });

        assertEquals("password does not match confirm password", exception.getMessage());
        verify(repository, never()).save(any());
    }
}
