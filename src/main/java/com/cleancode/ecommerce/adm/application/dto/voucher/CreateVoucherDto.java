package com.cleancode.ecommerce.adm.application.dto.voucher;

import com.cleancode.ecommerce.adm.domain.voucher.Discount;
import com.cleancode.ecommerce.adm.domain.voucher.Message;
import com.cleancode.ecommerce.adm.domain.voucher.TypeVoucher;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateVoucherDto {
    
	@NotBlank(message = "The message content cannot be null or empty")
    private String message;
	
    @NotNull(message = "The Type voucher content cannot be null or empty")
    private TypeVoucher typeVoucher;
    
    @NotBlank(message = "The Id Customer content cannot be null or empty")
    private String customerId;
    
    @Min(value = 1, message = "Discount content cannot be null or empty")
    private double discount;

	public CreateVoucherDto(String message, TypeVoucher typeVoucher, String customerId, double discount) {
		this.message = message;
		this.typeVoucher = typeVoucher;
		this.customerId = customerId;
		this.discount = discount;
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public Voucher execute() {
		return new Voucher(new CustomerId(customerId), new Message(message), typeVoucher, new Discount(discount));
	}
}