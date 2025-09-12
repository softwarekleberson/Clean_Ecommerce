package com.cleancode.ecommerce.customer.application.useCase.contract;

import java.util.List;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListAllCustomersDto;

public interface ListAllCustomers {

	public List<ListAllCustomersDto> execute();

}
