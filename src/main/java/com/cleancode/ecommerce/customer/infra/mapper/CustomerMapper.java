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
		entity.setCustomer_id(customer.getId().getValue().toString());
		return toEntity(customer, entity);
	}

	public static CustomerEntity toEntity(Customer domain, CustomerEntity entity) {
		entity.setCpf(domain.getCpf().getCpf());
		entity.setFull_name(domain.getName().getName());
		entity.setBirth_date(domain.getBirth().getBirth());
		entity.setPassword_hash(domain.getPassword().getPassword());
		entity.setGender(GenderEntity.valueOf(domain.getGender().name()));

		Phone phone = domain.getFullPhone();
		entity.setPhone(new PhoneEntity(phone.getDdd(), phone.getPhone(), PhoneTypeEntity.valueOf(phone.getTypePhone().name())));

		Email email = domain.getEmail();
		entity.setEmail(new EmailEntity(email.getEmail()));

		Set<String> domainDeliveryIds = domain.getDeliverys().stream()
				.map(Delivery::getPublicId)
				.collect(Collectors.toSet());

		entity.getDeliveryEntities().removeIf(e -> !domainDeliveryIds.contains(e.getPublic_id()));

		for (Delivery delivery : domain.getDeliverys()) {
			entity.getDeliveryEntities().stream()
					.filter(existing -> existing.getPublic_id().equals(delivery.getPublicId()))
					.findFirst()
					.ifPresentOrElse(
							existing -> DeliveryMapper.updateEntity(delivery, existing),
							() -> entity.getDeliveryEntities().add(DeliveryMapper.toEntity(delivery, entity))
					);
		}

		Set<String> domainChargeIds = domain.getCharges().stream()
				.map(Charge::getPublicId)
				.collect(Collectors.toSet());

		entity.getChargeEntities().removeIf(e -> !domainChargeIds.contains(e.getPublic_id()));

		for (Charge charge : domain.getCharges()) {
			entity.getChargeEntities().stream()
					.filter(existing -> existing.getPublic_id().equals(charge.getPublicId()))
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
				new CustomerId(entity.getCustomer_id()),
				new Name(entity.getFull_name()),
				Gender.valueOf(entity.getGender().name()),
				new Birth(entity.getBirth_date()),
				new Cpf(entity.getCpf()),
				new Contact(
						new Phone(
								entity.getPhone().getArea_code(),
								entity.getPhone().getPhone_number(),
								TypePhone.valueOf(entity.getPhone().getPhone_type().name())
						),
						new Email(entity.getEmail().getEmail())
				),
				new Password(entity.getPassword_hash())
		);

		if (entity.getDeliveryEntities() != null) {
			entity.getDeliveryEntities().forEach(e -> customer.registerDelivery(DeliveryMapper.toDomain(e)));
		}

		if (entity.getChargeEntities() != null) {
			entity.getChargeEntities().forEach(e -> customer.registerCharge(ChargeMapper.toDomain(e)));
		}

		return customer;
	}
}