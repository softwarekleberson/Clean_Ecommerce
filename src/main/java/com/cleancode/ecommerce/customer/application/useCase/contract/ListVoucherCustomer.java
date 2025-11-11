package com.cleancode.ecommerce.customer.application.useCase.contract;

import java.util.List;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListVoucherDto;

public interface ListVoucherCustomer {

	public List<ListVoucherDto> execute (String email);
}
