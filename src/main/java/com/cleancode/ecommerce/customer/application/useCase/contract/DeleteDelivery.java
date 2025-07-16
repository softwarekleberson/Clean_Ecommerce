package com.cleancode.ecommerce.customer.application.useCase.contract;

public interface DeleteDelivery {

	public void execute(String customerId, String deliveryId);
}
