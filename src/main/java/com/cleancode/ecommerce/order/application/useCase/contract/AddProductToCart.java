package com.cleancode.ecommerce.order.application.useCase.contract;

import com.cleancode.ecommerce.order.application.dtos.input.CreateCartDto;
import com.cleancode.ecommerce.order.application.dtos.output.ListCartDto;

public interface AddProductToCart {

	ListCartDto execute (CreateCartDto dto);
}
