package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.application.dtos.CreateDeliveryDto;

public interface CreateCustomerDelivery {

	void execute(String id, CreateDeliveryDto dto);
}
