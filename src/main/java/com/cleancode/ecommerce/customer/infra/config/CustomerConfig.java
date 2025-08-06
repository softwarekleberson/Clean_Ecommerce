package com.cleancode.ecommerce.customer.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerChargeImpl;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerDeliveryImpl;
import com.cleancode.ecommerce.customer.application.useCase.CreateCustomerImpl;
import com.cleancode.ecommerce.customer.application.useCase.DeleteChargeImpl;
import com.cleancode.ecommerce.customer.application.useCase.DeleteDeliveryImpl;
import com.cleancode.ecommerce.customer.application.useCase.ListCustomerImpl;
import com.cleancode.ecommerce.customer.application.useCase.UpdateChargeImpl;
import com.cleancode.ecommerce.customer.application.useCase.UpdateCustomerImpl;
import com.cleancode.ecommerce.customer.application.useCase.UpdateDeliveryImpl;
import com.cleancode.ecommerce.customer.application.useCase.UpdatePasswordImpl;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerCharge;
import com.cleancode.ecommerce.customer.application.useCase.contract.CreateCustomerDelivery;
import com.cleancode.ecommerce.customer.application.useCase.contract.DeleteCharge;
import com.cleancode.ecommerce.customer.application.useCase.contract.DeleteDelivery;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateCharge;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateCustomer;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdateDelivery;
import com.cleancode.ecommerce.customer.application.useCase.contract.UpdatePassword;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.event.EventPublisher;

@Configuration
public class CustomerConfig {

	@Bean
    public CreateCustomer createCustomer(
            CustomerRepository repository,
            EventPublisher eventPublisher
    		) 
				{
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
	public ListCustomer listCustomer (
			CustomerRepository repository) {
		return new ListCustomerImpl(repository);
	}
	
	@Bean
	public UpdateCustomer updateCustomer (
			CustomerRepository repository) {
		return new UpdateCustomerImpl(repository);
	}
	
	@Bean
	public UpdatePassword updatePassword (
			CustomerRepository repository) {
		return new UpdatePasswordImpl(repository);
	}
	
	@Bean
	public DeleteCharge deleteCharge (
			CustomerRepository repository) {
		return new DeleteChargeImpl(repository);
	}
	
	@Bean
	public DeleteDelivery deleteDelivery (
			CustomerRepository repository) {
		return new DeleteDeliveryImpl(repository);
	}
	
	@Bean
	public UpdateCharge updateCharge (
			CustomerRepository repositor) {
		return new UpdateChargeImpl(repositor);
	}
	
	@Bean
	public UpdateDelivery updateDelivery (
			CustomerRepository repositor) {
		return new UpdateDeliveryImpl(repositor);
	}
}