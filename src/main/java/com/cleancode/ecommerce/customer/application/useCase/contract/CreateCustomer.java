package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.application.dtos.CreateCustomerDto;

public interface CreateCustomer {

	void execute(CreateCustomerDto dto);

}
