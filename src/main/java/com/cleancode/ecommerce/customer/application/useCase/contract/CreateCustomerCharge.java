package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.application.dtos.address.CreateChargeDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;

public interface CreateCustomerCharge {

	ListCustomerDto execute(String id, CreateChargeDto dto);
}
