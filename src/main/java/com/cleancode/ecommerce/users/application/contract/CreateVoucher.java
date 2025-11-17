package com.cleancode.ecommerce.users.application.contract;

import com.cleancode.ecommerce.users.application.dto.voucher.CreateVoucherDto;

public interface CreateVoucher {

	public void execute (CreateVoucherDto dto);
}
