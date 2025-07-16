package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.application.dtos.customer.UpdatePasswordDto;

public interface UpdatePassword {

	public void updatePassword (String customerId, UpdatePasswordDto dto);
}
