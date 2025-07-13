package com.cleancode.ecommerce.customer.infra.mapper;

import java.util.HashSet;
import java.util.Set;

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

		// Atualiza apenas novos deliverys
		Set<String> existingDeliveryIds = new HashSet<>();
		entity.getDeliveryEntities().forEach(e -> existingDeliveryIds.add(e.getId()));

		for (Delivery delivery : customer.getDeliverys()) {
			if (!existingDeliveryIds.contains(delivery.getId())) {
				DeliveryEntity deliveryEntity = DeliveryMapper.toEntity(delivery, entity);
				entity.getDeliveryEntities().add(deliveryEntity);
			}
		}

		// Atualiza apenas novos charges
		Set<String> existingChargeIds = new HashSet<>();
		entity.getChargeEntities().forEach(e -> existingChargeIds.add(e.getId()));

		for (Charge charge : customer.getCharges()) {
			if (!existingChargeIds.contains(charge.getId())) {
				ChargeEntity chargeEntity = ChargeMapper.toEntity(charge, entity);
				entity.getChargeEntities().add(chargeEntity);
			}
		}

		return entity;
	}

	public static CustomerEntity toEntity(Customer domain, CustomerEntity entity) {
		entity.setCpf(domain.getCpf().getCpf());
		entity.setActive(domain.isActive());
		entity.setName(domain.getName().getName());
		entity.setBirth(domain.getBirth().getBirth());
		entity.setPassword(domain.getPassword().getPassword());
		entity.setGender(GenderEntity.valueOf(domain.getGender().name()));

		Phone phone = domain.getFullPhone();
		entity.setPhone(new PhoneEntity(phone.getDdd(), phone.getPhone(),
				TypePhoneEntity.valueOf(phone.getTypePhone().name())));

		Email email = domain.getEmail();
		entity.setEmail(new EmailEntity(email.getEmail()));

		for (Delivery delivery : domain.getDeliverys()) {
			boolean exists = entity.getDeliveryEntities().stream()
					.anyMatch(existing -> existing.getId().equals(delivery.getId()));

			if (!exists) {
				DeliveryEntity newEntity = DeliveryMapper.toEntity(delivery, entity);
				entity.getDeliveryEntities().add(newEntity);
			}
		}

		for (Charge charge : domain.getCharges()) {
			boolean exists = entity.getChargeEntities().stream()
					.anyMatch(existing -> existing.getId().equals(charge.getId()));

			if (!exists) {
				ChargeEntity newEntity = ChargeMapper.toEntity(charge, entity);
				entity.getChargeEntities().add(newEntity);
			}
		}

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
			entity.getDeliveryEntities().forEach(e -> customer.insertNewDelivery(DeliveryMapper.toDomain(e)));
		}

		if (entity.getChargeEntities() != null) {
			entity.getChargeEntities().forEach(e -> customer.insertNewCharge(ChargeMapper.toDomain(e)));
		}

		return customer;
	}
}