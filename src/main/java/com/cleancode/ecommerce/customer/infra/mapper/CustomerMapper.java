package com.cleancode.ecommerce.customer.infra.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.customer.domain.customer.*;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.*;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

public final class CustomerMapper {

	private CustomerMapper() {}

	public static CustomerEntity toEntity(Customer customer) {
		CustomerEntity entity = new CustomerEntity();
		entity.setId(customer.getId().getValue().toString());
		return toEntity(customer, entity);
	}

	public static CustomerEntity toEntity(Customer domain, CustomerEntity entity) {
		entity.setCpf(domain.getCpf().getCpf());
		entity.setName(domain.getName().getName());
		entity.setBirth(domain.getBirth().getBirth());
		entity.setPassword(domain.getPassword().getPassword());
		entity.setGender(GenderEntity.valueOf(domain.getGender().name()));

		Phone phone = domain.getFullPhone();
		entity.setPhone(new PhoneEntity(phone.getDdd(), phone.getPhone(), TypePhoneEntity.valueOf(phone.getTypePhone().name())));

		Email email = domain.getEmail();
		entity.setEmail(new EmailEntity(email.getEmail()));

		Set<String> domainDeliveryIds = domain.getDeliverys().stream()
				.map(Delivery::getId)
				.collect(Collectors.toSet());

		entity.getDeliveryEntities().removeIf(e -> !domainDeliveryIds.contains(e.getId()));

		for (Delivery delivery : domain.getDeliverys()) {
			entity.getDeliveryEntities().stream()
					.filter(existing -> existing.getId().equals(delivery.getId()))
					.findFirst()
					.ifPresentOrElse(
							existing -> DeliveryMapper.updateEntity(delivery, existing),
							() -> entity.getDeliveryEntities().add(DeliveryMapper.toEntity(delivery, entity))
					);
		}

		Set<String> domainChargeIds = domain.getCharges().stream()
				.map(Charge::getId)
				.collect(Collectors.toSet());

		entity.getChargeEntities().removeIf(e -> !domainChargeIds.contains(e.getId()));

		for (Charge charge : domain.getCharges()) {
			entity.getChargeEntities().stream()
					.filter(existing -> existing.getId().equals(charge.getId()))
					.findFirst()
					.ifPresentOrElse(
							existing -> ChargeMapper.updateEntity(charge, existing),
							() -> entity.getChargeEntities().add(ChargeMapper.toEntity(charge, entity))
					);
		}

		return entity;
	}

	public static Customer toDomain(CustomerEntity entity) {
		Customer customer = new Customer(
				new Id(entity.getId()),
				new Name(entity.getName()),
				Gender.valueOf(entity.getGender().name()),
				new Birth(entity.getBirth()),
				new Cpf(entity.getCpf()),
				new Contact(
						new Phone(
								entity.getPhone().getDdd(),
								entity.getPhone().getPhone(),
								TypePhone.valueOf(entity.getPhone().getTypePhone().name())
						),
						new Email(entity.getEmail().getEmail())
				),
				new Password(entity.getPassword())
		);

		if (entity.getDeliveryEntities() != null) {
			entity.getDeliveryEntities().forEach(e -> customer.insertNewDelivery(DeliveryMapper.toDomain(e)));
		}

		if (entity.getChargeEntities() != null) {
			entity.getChargeEntities().forEach(e -> customer.insertNewCharge(ChargeMapper.toDomain(e)));
		}

		return customer;
	}
}