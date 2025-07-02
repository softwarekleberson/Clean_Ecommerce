package com.cleancode.ecommerce.customer.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.application.dtos.CreateChargeDto;

public interface CreateCustomerCharge {

	void execute(UUID id, CreateChargeDto dto);
}
