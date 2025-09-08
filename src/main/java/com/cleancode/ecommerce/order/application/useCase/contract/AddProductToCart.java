package com.cleancode.ecommerce.order.application.useCase.contract;

import com.cleancode.ecommerce.order.application.dtos.input.CreateCartDto;
import com.cleancode.ecommerce.order.application.dtos.output.CartDto;

public interface AddProductToCart {

	CartDto execute (CreateCartDto dto);
}
