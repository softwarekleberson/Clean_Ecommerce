package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.application.dtos.address.UpdateAddressDto;
import com.cleancode.ecommerce.customer.application.dtos.customer.ListCustomerDto;

public interface UpdateDelivery {

	public ListCustomerDto execute(String clienteId, String id, UpdateAddressDto dto);

}
