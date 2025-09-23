package com.cleancode.ecommerce.order.application.useCase.contract;

import com.cleancode.ecommerce.order.application.dtos.input.DeleteUniqueProductToCartDto;

public interface DeleteUniqueProductCart {

	void execute(String customerId, DeleteUniqueProductToCartDto dto);
}
