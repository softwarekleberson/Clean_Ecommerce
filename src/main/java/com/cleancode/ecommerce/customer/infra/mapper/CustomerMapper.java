package com.cleancode.ecommerce.customer.infra.mapper;

import com.cleancode.ecommerce.customer.domain.customer.Birth;
import com.cleancode.ecommerce.customer.domain.customer.Contact;
import com.cleancode.ecommerce.customer.domain.customer.Customer;
import com.cleancode.ecommerce.customer.domain.customer.Gender;
import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.Password;
import com.cleancode.ecommerce.customer.domain.customer.Phone;
import com.cleancode.ecommerce.customer.domain.customer.TypePhone;
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

		return entity;
	}

	public static Customer toDomain(CustomerEntity entity) {
		return new Customer(new Id(entity.getId()), new Name(entity.getName()),
				Gender.valueOf(entity.getGender().name()), new Birth(entity.getBirth()), new Cpf(entity.getCpf()),
				new Contact(
						new Phone(entity.getPhone().getDdd(), entity.getPhone().getPhone(),
								TypePhone.valueOf(entity.getPhone().getTypePhone().name())),
						new Email(entity.getEmail().getEmail())),
				new Password(entity.getPassword()));
	}
}
