package com.cleancode.ecommerce.customer.domain.customer;

public class SystemClientStatus {

	private boolean systemClientStatus;
	
	public SystemClientStatus(boolean systemClientStatus) {
		this.systemClientStatus = systemClientStatus;
	}
	
	public boolean isSystemClientStatus() {
		return systemClientStatus;
	}

	public static SystemClientStatus changeStatus(boolean newStatus) {
		newStatus = !newStatus;
		return new SystemClientStatus(newStatus);
	}
}
