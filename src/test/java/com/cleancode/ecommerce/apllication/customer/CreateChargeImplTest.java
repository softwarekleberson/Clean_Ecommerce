package com.cleancode.ecommerce.apllication.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.cleancode.ecommerce.customer.application.dtos.CreateChargeDto;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerChargeImpl;
import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

public class CreateChargeImplTest {

    private CustomerRepository repository;
    private CreateCustomerChargeImpl useCase;

    @BeforeEach
    void setup() {
        repository = mock(CustomerRepository.class);
        useCase = new CreateCustomerChargeImpl(repository);
    }

    @Test
    void shouldAddChargeToCustomerSuccessfully() {

    	String customerId = UUID.randomUUID().toString();
        CreateChargeDto dto = new CreateChargeDto(
                "João", "Rua A", "123", "Centro", "00000-000", "Deixar na portaria",
                "Rua", "Apartamento", "CidadeX", "EstadoY", "Brasil"
        );
        Customer customer = mock(Customer.class);
        when(repository.getCustomerById(customerId)).thenReturn(Optional.of(customer));

        useCase.execute(customerId, dto);

        ArgumentCaptor<Charge> captor = ArgumentCaptor.forClass(Charge.class);
        verify(customer).insertNewCharge(captor.capture());

        Charge captured = captor.getValue();
        assertEquals("João", captured.getReceiver());
        assertEquals("Rua A", captured.getStreet());
        assertEquals("123", captured.getNumber());
        assertEquals("Centro", captured.getNeighborhood());
        assertEquals("00000-000", captured.getZipCode());
        assertEquals("Deixar na portaria", captured.getObservation());
        assertEquals("Rua", captured.getStreetType());
        assertEquals("Apartamento", captured.getTypeResidence());
        assertEquals("CidadeX", captured.getCity());
        assertEquals("EstadoY", captured.getState());
        assertEquals("Brasil", captured.getCountry());

        verify(repository).save(customer);
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {

    	String customerId = UUID.randomUUID().toString();
        CreateChargeDto dto = new CreateChargeDto(
                "João", "Rua A", "123", "Centro", "00000-000", "Deixar na portaria",
                "Rua", "Apartamento", "CidadeX", "EstadoY", "Brasil"
        );

        when(repository.getCustomerById(customerId)).thenReturn(Optional.empty());
        assertThrows(IllegalDomainException.class, () -> useCase.execute(customerId, dto));
        verify(repository, never()).save(any());
    }
}
