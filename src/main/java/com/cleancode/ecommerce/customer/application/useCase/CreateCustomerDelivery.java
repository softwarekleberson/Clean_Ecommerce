package com.cleancode.ecommerce.customer.application.useCase;

import java.util.UUID;

import com.cleancode.ecommerce.customer.application.dtos.CreateDeliveryDto;

public interface CreateCustomerDelivery {

	void execute(UUID id, CreateDeliveryDto dto);
}
