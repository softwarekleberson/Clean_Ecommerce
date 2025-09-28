package com.cleancode.ecommerce.adm.application.contract;

import com.cleancode.ecommerce.adm.application.dto.adm.CreateVoucherDto;

public interface CreateVoucherPromotional {

	public void execute (String emailAdm, CreateVoucherDto dto);
}
