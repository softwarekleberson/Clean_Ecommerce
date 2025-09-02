package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.dtos.address.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerDeliveryImpl;
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

class CreateCustomerDeliveryImplTest {

    private CustomerRepository repository;
    private CreateCustomerDeliveryImpl useCase;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        useCase = new CreateCustomerDeliveryImpl(repository);
    }

    private Customer buildCustomer() {
        return new Customer(
                new CustomerId("123"),
                new Name("João Silva"),
                Gender.MALE,
                new Birth(LocalDate.of(1990, 1, 1)),
                new Cpf("123.456.789-01"),
                new Contact(new Phone("11", "999999999", TypePhone.LANDLINE), new Email("joao@email.com")),
                new Password("senha123")
        );
    }

    @Test
    void deveCriarDeliveryQuandoClienteExistir() {
        // Arrange
        Customer customer = buildCustomer();
        when(repository.getCustomerById("123")).thenReturn(Optional.of(customer));

        CreateDeliveryDto dto = mock(CreateDeliveryDto.class);
        Delivery delivery = new Delivery("d1", "Frase Entrega", "Maria", "Rua A", "123",
                "Centro", "12345-678", "Obs", "Rua", "Casa", "São Paulo", "SP", "Brasil");
        when(dto.createDelivery()).thenReturn(delivery);

        // Act
        ListCustomerDto result = useCase.execute("123", dto);

        // Assert
        assertNotNull(result);
        assertEquals("João Silva", result.name());
        assertEquals(1, customer.getDeliverys().size());
        assertEquals(delivery, customer.getDeliverys().get(0));

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository, times(1)).save(captor.capture());
        assertEquals(1, captor.getValue().getDeliverys().size());
    }

    @Test
    void deveLancarExcecaoQuandoClienteNaoExistir() {
        // Arrange
        when(repository.getCustomerById("999")).thenReturn(Optional.empty());
        CreateDeliveryDto dto = mock(CreateDeliveryDto.class);

        // Act & Assert
        IllegalDomainException ex = assertThrows(
                IllegalDomainException.class,
                () -> useCase.execute("999", dto)
        );

        assertEquals("Customer with id : 999 not found", ex.getMessage());
        verify(repository, never()).save(any());
    }
}
