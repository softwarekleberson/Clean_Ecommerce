package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.application.dtos.address.CreateDeliveryDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;

public interface CreateCustomerDelivery {

	ListCustomerDto execute(String id, CreateDeliveryDto dto);
}
