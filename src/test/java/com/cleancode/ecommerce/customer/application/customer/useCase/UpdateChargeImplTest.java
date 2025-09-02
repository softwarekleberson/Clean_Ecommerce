package com.cleancode.ecommerce.customer.application.customer.useCase;


import com.cleancode.ecommerce.customer.application.dtos.address.UpdateAddressDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.UpdateChargeImpl;
import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateChargeImplTest {

    private CustomerRepository repository;
    private UpdateChargeImpl updateChargeUseCase;

    @BeforeEach
    void setUp() {
        repository = mock(CustomerRepository.class);
        updateChargeUseCase = new UpdateChargeImpl(repository);
    }

    private Customer buildCustomerWithCharge() {
        Customer customer = new Customer(
                new com.cleancode.ecommerce.customer.domain.customer.CustomerId("123"),
                new com.cleancode.ecommerce.shared.kernel.Name("John Doe"),
                com.cleancode.ecommerce.customer.domain.customer.Gender.MALE,
                new com.cleancode.ecommerce.customer.domain.customer.Birth(LocalDate.of(1990, 1, 1)),
                new com.cleancode.ecommerce.shared.kernel.Cpf("123.456.789-01"),
                new com.cleancode.ecommerce.customer.domain.customer.Contact(
                        new com.cleancode.ecommerce.customer.domain.customer.Phone("11", "999999999", com.cleancode.ecommerce.customer.domain.customer.TypePhone.LANDLINE),
                        new com.cleancode.ecommerce.shared.kernel.Email("john@example.com")
                ),
                new com.cleancode.ecommerce.customer.domain.customer.Password("password123")
        );

        // Add a dummy charge
        customer.registerCharge(new Charge(
                "c1", "Receiver", "Street", "123", "Neighborhood", "12345-678",
                "Observation", "StreetType", "House", "City", "State", "Country"
        ));

        return customer;
    }

    @Test
    void shouldUpdateChargeSuccessfully() {
        // Arrange
        Customer customer = buildCustomerWithCharge();
        when(repository.getCustomerById("123")).thenReturn(java.util.Optional.of(customer));

        UpdateAddressDto dto = mock(UpdateAddressDto.class);
        when(dto.receiver()).thenReturn("New Receiver");
        when(dto.street()).thenReturn("New Street");
        when(dto.number()).thenReturn("456");
        when(dto.neighborhood()).thenReturn("New Neighborhood");
        when(dto.zipCode()).thenReturn("98765-432");
        when(dto.observation()).thenReturn("New Observation");
        when(dto.streetType()).thenReturn("New StreetType");
        when(dto.typeResidence()).thenReturn("Apartment");
        when(dto.city()).thenReturn("New City");
        when(dto.state()).thenReturn("New State");
        when(dto.country()).thenReturn("New Country");

        // Act
        ListCustomerDto result = updateChargeUseCase.execute("123", "c1", dto);

        // Assert
        Charge updatedCharge = customer.findChargeById("c1");
        assertEquals("New Receiver", updatedCharge.getReceiver());
        assertEquals("New Street", updatedCharge.getStreet());
        assertEquals("456", updatedCharge.getNumber());
        assertEquals("New Neighborhood", updatedCharge.getNeighborhood());
        assertEquals("New Observation", updatedCharge.getObservation());
        assertEquals("New StreetType", updatedCharge.getStreetType());
        assertEquals("Apartment", updatedCharge.getResidenceType());
        assertEquals("New City", updatedCharge.getCity());
        assertEquals("New State", updatedCharge.getState());
        assertEquals("New Country", updatedCharge.getCountry());

        ArgumentCaptor<Customer> captor = ArgumentCaptor.forClass(Customer.class);
        verify(repository, times(1)).save(captor.capture());
        assertEquals(customer, captor.getValue());

        assertNotNull(result);
        assertEquals(customer.getName().getName(), result.name());
    }

    @Test
    void shouldThrowWhenCustomerNotFound() {
        // Arrange
        when(repository.getCustomerById("999")).thenReturn(java.util.Optional.empty());

        // Act & Assert
        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> updateChargeUseCase.execute("999", "c1", mock(UpdateAddressDto.class))
        );

        assertEquals("Customer with id : c1 not found", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowWhenChargeNotFound() {
        // Arrange
        Customer customer = buildCustomerWithCharge();
        when(repository.getCustomerById("123")).thenReturn(java.util.Optional.of(customer));

        // Act & Assert
        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> updateChargeUseCase.execute("123", "nonexistent-charge", mock(UpdateAddressDto.class))
        );

        assertEquals("Id Charge not found", exception.getMessage());
        verify(repository, never()).save(any());
    }
}