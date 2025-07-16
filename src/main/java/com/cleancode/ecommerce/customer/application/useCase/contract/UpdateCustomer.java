package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.application.dtos.UpdateCustomerDto;

public interface UpdateCustomer {

	public void updateCustomer(String customerId, UpdateCustomerDto dto);
}
