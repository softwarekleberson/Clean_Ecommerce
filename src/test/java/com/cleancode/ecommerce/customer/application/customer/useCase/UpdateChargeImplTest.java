package com.cleancode.ecommerce.customer.application.customer.useCase;


import com.cleancode.ecommerce.customer.application.dtos.address.UpdateAddressDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.UpdateChargeImpl;
import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Charge;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.SystemClientStatus;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

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
                new CustomerId("123"),
                new Name("John Doe"),
                Gender.MALE,
                new Birth(LocalDate.of(1990, 1, 1)),
                new Cpf("123.456.789-01"),
                new Contact(
                        new Phone("11", "999999999", TypePhone.LANDLINE),
                        new Email("john@example.com")
                ),
                new Password("password123"),
                new SystemClientStatus(true)
        );

        customer.registerCharge(new Charge(
                "c1", "Receiver", "Street", "123", "Neighborhood", "12345678",
                "Observation", "StreetType", "House", "City", "State", "Country"
        ));

        return customer;
    }

    @Test
    void shouldUpdateChargeSuccessfully() {
        Customer customer = buildCustomerWithCharge();
        when(repository.getCustomerById("123")).thenReturn(java.util.Optional.of(customer));

        UpdateAddressDto dto = mock(UpdateAddressDto.class);
        when(dto.receiver()).thenReturn("New Receiver");
        when(dto.street()).thenReturn("New Street");
        when(dto.number()).thenReturn("456");
        when(dto.neighborhood()).thenReturn("New Neighborhood");
        when(dto.zipCode()).thenReturn("98765432");
        when(dto.observation()).thenReturn("New Observation");
        when(dto.streetType()).thenReturn("New StreetType");
        when(dto.typeResidence()).thenReturn("Apartment");
        when(dto.city()).thenReturn("New City");
        when(dto.state()).thenReturn("New State");
        when(dto.country()).thenReturn("New Country");

        ListCustomerDto result = updateChargeUseCase.execute("123", "c1", dto);

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
        when(repository.getCustomerById("999")).thenReturn(java.util.Optional.empty());

        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> updateChargeUseCase.execute("999", "c1", mock(UpdateAddressDto.class))
        );

        assertEquals("Customer with id : c1 not found", exception.getMessage());
        verify(repository, never()).save(any());
    }

    @Test
    void shouldThrowWhenChargeNotFound() {
        Customer customer = buildCustomerWithCharge();
        when(repository.getCustomerById("123")).thenReturn(java.util.Optional.of(customer));

        IllegalDomainException exception = assertThrows(
                IllegalDomainException.class,
                () -> updateChargeUseCase.execute("123", "nonexistent-charge", mock(UpdateAddressDto.class))
        );

        assertEquals("Id Charge not found", exception.getMessage());
        verify(repository, never()).save(any());
    }
}