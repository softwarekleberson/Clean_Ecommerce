package com.cleancode.ecommerce.customer.infra.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.customer.domain.customer.*;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.ChargeEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.address.DeliveryEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.CustomerEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.EmailEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.GenderEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.PhoneEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.TypePhoneEntity;
import com.cleancode.ecommerce.shared.kernel.Cpf;
import com.cleancode.ecommerce.shared.kernel.Email;
import com.cleancode.ecommerce.shared.kernel.Name;

public final class CustomerMapper {

	private CustomerMapper() {
	}

	public static CustomerEntity toEntity(Customer customer) {
		CustomerEntity entity = new CustomerEntity();

		entity.setId(customer.getId().getValue());
		entity.setCpf(customer.getCpf().getCpf());
		entity.setActive(customer.isActive());
		entity.setName(customer.getName().getName());
		entity.setBirth(customer.getBirth().getBirth());
		entity.setPassword(customer.getPassword().getPassword());
		entity.setGender(GenderEntity.valueOf(customer.getGender().name()));

		Phone phone = customer.getFullPhone();
		entity.setPhone(new PhoneEntity(phone.getDdd(), phone.getPhone(),
				TypePhoneEntity.valueOf(phone.getTypePhone().name())));

		Email email = customer.getEmail();
		entity.setEmail(new EmailEntity(email.getEmail()));

		List<DeliveryEntity> deliveryEntities = customer.getDeliverys() != null ? customer.getDeliverys().stream()
				.map(delivery -> DeliveryMapper.toEntity(delivery, entity)).collect(Collectors.toList()) : List.of();
		entity.setDeliveryEntities(deliveryEntities);

		List<ChargeEntity> chargeEntities = customer.getCharges() != null ? customer.getCharges().stream()
				.map(charge -> ChargeMapper.toEntity(charge, entity)).collect(Collectors.toList()) : List.of();
		entity.setChargeEntities(chargeEntities);

		return entity;
	}

	public static Customer toDomain(CustomerEntity entity) {
		Customer customer = new Customer(new Id(entity.getId()), new Name(entity.getName()),
				Gender.valueOf(entity.getGender().name()), new Birth(entity.getBirth()), new Cpf(entity.getCpf()),
				new Contact(
						new Phone(entity.getPhone().getDdd(), entity.getPhone().getPhone(),
								TypePhone.valueOf(entity.getPhone().getTypePhone().name())),
						new Email(entity.getEmail().getEmail())),
				new Password(entity.getPassword()));

		if (entity.getDeliveryEntities() != null) {
			entity.getDeliveryEntities().stream().map(DeliveryMapper::toDomain).forEach(customer::insertNewDelivery);
		}

		if (entity.getChargeEntities() != null) {
			entity.getChargeEntities().stream().map(ChargeMapper::toDomain).forEach(customer::insertNewCharge);
		}

		return customer;
	}
}