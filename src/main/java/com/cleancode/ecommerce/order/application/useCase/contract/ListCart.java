package com.cleancode.ecommerce.order.application.useCase.contract;

import com.cleancode.ecommerce.order.application.dtos.output.CartDto;

public interface ListCart {

	CartDto execute (String customerId);
}
