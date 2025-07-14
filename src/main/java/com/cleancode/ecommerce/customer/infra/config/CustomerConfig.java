package com.cleancode.ecommerce.customer.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.customer.application.useCase.CreateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerCharge;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerChargeImpl;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerDelivery;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerDeliveryImpl;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerImpl;
import com.cleancode.ecommerce.customer.application.useCase.ListCustomer;
import com.cleancode.ecommerce.customer.application.useCase.ListCustomerImpl;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.shared.domain.customer.event.EventPublisher;

@Configuration
public class CustomerConfig {

	@Bean
    public CreateCustomer createCustomer(
            CustomerRepository repository,
            EventPublisher eventPublisher) {
        return new CreateCustomerImpl(repository, eventPublisher);
    }
	
	@Bean
	public CreateCustomerDelivery createCustomerDelivery(
            CustomerRepository repository) {
		return new CreateCustomerDeliveryImpl(repository);
	}
	
	@Bean
	public CreateCustomerCharge createCustomerCharge (
			CustomerRepository repository) {
		return new CreateCustomerChargeImpl(repository);
	}
	
	@Bean
	public ListCustomer ListCustomer (
			CustomerRepository repository) {
		return new ListCustomerImpl(repository);
	}
}
