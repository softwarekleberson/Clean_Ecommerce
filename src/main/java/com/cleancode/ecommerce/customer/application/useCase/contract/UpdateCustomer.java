package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.UpdateCustomerDto;

public interface UpdateCustomer {

	public ListCustomerDto execute(String customerId, UpdateCustomerDto dto);
}
