package com.cleancode.ecommerce.adm.application.contract;

import com.cleancode.ecommerce.adm.application.dto.voucher.CreateVoucherDto;

public interface CreateVoucherPromotional {

	public void execute (String emailAdm, CreateVoucherDto dto);
}
