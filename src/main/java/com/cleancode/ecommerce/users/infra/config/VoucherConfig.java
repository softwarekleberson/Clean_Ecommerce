package com.cleancode.ecommerce.users.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.customer.application.useCase.ListVoucherCustomerImpl;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListVoucherCustomer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;
import com.cleancode.ecommerce.users.application.CreateVoucherImpl;
import com.cleancode.ecommerce.users.application.contract.CreateVoucher;
import com.cleancode.ecommerce.users.domain.voucher.repository.VoucherRepository;

@Configuration
public class VoucherConfig {

	@Bean
	public CreateVoucher createVoucher(VoucherRepository repository, CustomerRepository customerRepository) {
		return new CreateVoucherImpl(repository, customerRepository);
	}
	
	@Bean
	public ListVoucherCustomer listVoucherCustomer (VoucherRepository repository, CustomerRepository customerRepository) {
		return new ListVoucherCustomerImpl(repository, customerRepository); 
	}
}
