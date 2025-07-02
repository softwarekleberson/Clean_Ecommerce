package com.cleancode.ecommerce.apllication.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.application.dtos.CreateCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerImpl;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.customer.domain.customer.repository.PasswordEncryptor;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.shared.domain.customer.event.EventPublisher;
import com.cleancode.ecommerce.customer.domain.customer.event.EventNewCustomer;

public class CreateCustomerImplTest {

    private CustomerRepository repository;
    private PasswordEncryptor passwordEncryptor;
    private EventPublisher eventPublisher;
    private CreateCustomerImpl createCustomer;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        passwordEncryptor = mock(PasswordEncryptor.class);
        eventPublisher = mock(EventPublisher.class);

        createCustomer = new CreateCustomerImpl(repository, passwordEncryptor, eventPublisher);
    }

    @Test
    void deveCriarClienteComSucesso() {
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

        when(passwordEncryptor.encryptPassword("senha123")).thenReturn("senha123_encrypted");

        createCustomer.execute(dto);

        verify(repository, times(1)).save(any(Customer.class));
        verify(eventPublisher, times(1)).process(any(EventNewCustomer.class));
    }

    @Test
    void deveLancarExcecaoQuandoSenhasNaoConferem() {
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
        verify(eventPublisher, never()).process(any());
    }
}
