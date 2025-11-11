package com.cleancode.ecommerce.customer.application.useCase.contract;

public interface DeleteCharge {

	public void execute (String email, String ChargeId);
}
