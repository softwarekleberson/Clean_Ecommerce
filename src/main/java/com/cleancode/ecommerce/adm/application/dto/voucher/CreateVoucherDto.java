package com.cleancode.ecommerce.adm.application.dto.voucher;

import com.cleancode.ecommerce.adm.domain.voucher.CodeVoucher;
import com.cleancode.ecommerce.adm.domain.voucher.Discount;
import com.cleancode.ecommerce.adm.domain.voucher.Message;
import com.cleancode.ecommerce.adm.domain.voucher.Promotional;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CreateVoucherDto {
	
    @NotBlank(message = "The Message content cannot be null or empty")
	private String messageContent;
	
    @NotBlank(message = "The Code cannot be null or empty")
	private String code;
	
    @Min(value = 1, message = "The discount must be at least 1")
	private int discountValue;

	public CreateVoucherDto(String messageContent, String code, int discountValue) {
		this.messageContent = messageContent;
		this.code = code;
		this.discountValue = discountValue;
	}

	public Promotional create() {
		Message message = new Message(this.messageContent);
		CodeVoucher codeVoucher = new CodeVoucher(this.code);
		Discount discount = new Discount(this.discountValue);
		return new Promotional(message, codeVoucher, discount);
	}
}
