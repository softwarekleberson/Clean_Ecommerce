package com.cleancode.ecommerce.adm.application.dto.voucher;

import java.time.LocalDate;

import com.cleancode.ecommerce.adm.domain.voucher.Discount;
import com.cleancode.ecommerce.adm.domain.voucher.Message;
import com.cleancode.ecommerce.adm.domain.voucher.Replacement;
import com.cleancode.ecommerce.adm.domain.voucher.TypeVoucher;
import com.cleancode.ecommerce.adm.domain.voucher.VoucherId;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateVoucherDto {
    
	@NotBlank(message = "Email adm is required")
	private String emailAdm;
	
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

	public String getEmailAdm() {
		return emailAdm;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public Replacement execute() {
		VoucherId voucherId = new VoucherId();
		LocalDate emission = LocalDate.now();
		Message message = new Message(this.message);
		CustomerId customerId = new CustomerId(this.customerId);
		Discount discount = new Discount(this.discount);
		return new Replacement(voucherId, message, emission, typeVoucher, customerId, discount);
	}
}