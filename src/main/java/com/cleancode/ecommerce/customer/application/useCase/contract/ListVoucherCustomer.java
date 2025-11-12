package com.cleancode.ecommerce.customer.application.useCase.contract;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListVoucherDto;

public interface ListVoucherCustomer {

	public Page<ListVoucherDto> execute(String email, Pageable pageable);
}
