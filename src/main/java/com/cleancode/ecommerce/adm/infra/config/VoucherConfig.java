package com.cleancode.ecommerce.adm.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cleancode.ecommerce.adm.application.CreateVoucherImpl;
import com.cleancode.ecommerce.adm.application.contract.CreateVoucher;
import com.cleancode.ecommerce.adm.domain.voucher.repository.VoucherRepository;
import com.cleancode.ecommerce.customer.application.useCase.ListVoucherCustomerImpl;
import com.cleancode.ecommerce.customer.application.useCase.contract.ListVoucherCustomer;
import com.cleancode.ecommerce.customer.domain.customer.repository.CustomerRepository;

@Configuration
public class VoucherConfig {

	@Bean
	public CreateVoucher createVoucher(VoucherRepository repository, CustomerRepository customerRepository) {
		return new CreateVoucherImpl(repository, customerRepository);
	}
	
	@Bean
	public ListVoucherCustomer listVoucherCustomer (VoucherRepository repository) {
		return new ListVoucherCustomerImpl(repository); 
	}
}
