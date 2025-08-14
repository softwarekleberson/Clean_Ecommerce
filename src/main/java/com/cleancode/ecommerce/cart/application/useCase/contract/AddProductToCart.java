package com.cleancode.ecommerce.cart.application.useCase.contract;

import com.cleancode.ecommerce.cart.application.dtos.input.CreateCartDto;
import com.cleancode.ecommerce.cart.application.dtos.output.ListCartDto;

public interface AddProductToCart {

	ListCartDto execute (CreateCartDto dto);
}
