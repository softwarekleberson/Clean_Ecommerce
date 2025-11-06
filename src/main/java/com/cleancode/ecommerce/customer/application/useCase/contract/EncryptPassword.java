package com.cleancode.ecommerce.customer.application.useCase.contract;

import com.cleancode.ecommerce.customer.domain.customer.Customer;

public interface EncryptPassword {

    public void execute (Customer customer);
}
