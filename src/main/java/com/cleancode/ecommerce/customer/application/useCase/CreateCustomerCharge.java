package com.cleancode.ecommerce.customer.application.useCase;

import com.cleancode.ecommerce.customer.application.dtos.CreateChargeDto;

public interface CreateCustomerCharge {

	void execute(String id, CreateChargeDto dto);
}
