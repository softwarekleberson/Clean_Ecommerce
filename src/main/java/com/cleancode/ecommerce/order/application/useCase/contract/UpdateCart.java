package com.cleancode.ecommerce.order.application.useCase.contract;

import com.cleancode.ecommerce.order.application.dtos.input.UpdateCartDto;
import com.cleancode.ecommerce.order.application.dtos.output.CartDto;

public interface UpdateCart {

	CartDto execute(String email, UpdateCartDto dto);
}
