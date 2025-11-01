package com.cleancode.ecommerce.customer.application.customer.useCase;

import com.cleancode.ecommerce.customer.application.dtos.address.UpdateAddressDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.useCase.UpdateDeliveryImpl;
import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Delivery;
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

class UpdateDeliveryImplTest {

	private CustomerRepository repository;
	private UpdateDeliveryImpl updateDeliveryUseCase;

	@BeforeEach
	void setUp() {
		repository = mock(CustomerRepository.class);
		updateDeliveryUseCase = new UpdateDeliveryImpl(repository);
	}

	private Customer buildCustomerWithDelivery() {
		Customer customer = new Customer(new com.cleancode.ecommerce.customer.domain.customer.CustomerId("123"),
				new Name("John Doe"),
				Gender.MALE,
				new Birth(LocalDate.of(1990, 1, 1)),
				new Cpf("123.456.789-01"),
				new Contact(
						new Phone("11", "999999999",
						TypePhone.LANDLINE),
						new Email("john@example.com")),
				new Password("password123"),
				 new SystemClientStatus(true));

		customer.registerDelivery(new Delivery("d1", true, "Delivery Phrase", "Receiver", "Street", "123", "Neighborhood",
				"12345678", "Observation", "StreetType", "House", "City", "State", "Country"));

		return customer;
	}

	@Test
	void shouldUpdateDeliverySuccessfully() {
		Customer customer = buildCustomerWithDelivery();
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
		when(dto.deliveryPhrase()).thenReturn("New Phrase");

		ListCustomerDto result = updateDeliveryUseCase.execute("123", "d1", dto);

		Delivery updatedDelivery = customer.findDeliveryById("d1");
		assertEquals("New Receiver", updatedDelivery.getReceiver());
		assertEquals("New Street", updatedDelivery.getStreet());
		assertEquals("456", updatedDelivery.getNumber());
		assertEquals("New Neighborhood", updatedDelivery.getNeighborhood());
		assertEquals("New Observation", updatedDelivery.getObservation());
		assertEquals("New StreetType", updatedDelivery.getStreetType());
		assertEquals("New City", updatedDelivery.getCity());
		assertEquals("New State", updatedDelivery.getState());
		assertEquals("New Country", updatedDelivery.getCountry());
		assertEquals("New Phrase", updatedDelivery.getDeliveryPhrase());

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
                () -> updateDeliveryUseCase.execute("999", "d1", mock(UpdateAddressDto.class))
        );

        assertEquals("Customer with id : d1 not found", exception.getMessage());
        verify(repository, never()).save(any());
    }

	@Test
	void shouldThrowWhenDeliveryNotFound() {
		Customer customer = buildCustomerWithDelivery();
		when(repository.getCustomerById("123")).thenReturn(java.util.Optional.of(customer));

		IllegalDomainException exception = assertThrows(IllegalDomainException.class,
				() -> updateDeliveryUseCase.execute("123", "nonexistent-delivery", mock(UpdateAddressDto.class)));

		assertEquals("Id Delivery not found", exception.getMessage());
		verify(repository, never()).save(any());
	}
}