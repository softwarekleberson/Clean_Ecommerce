package com.cleancode.ecommerce.adm.application;

import java.util.Optional;

import com.cleancode.ecommerce.adm.application.contract.CreateVoucherPromotional;
import com.cleancode.ecommerce.adm.application.dto.adm.CreateVoucherDto;
import com.cleancode.ecommerce.adm.domain.adm.Adm;
import com.cleancode.ecommerce.adm.domain.adm.repository.AdmRepository;
import com.cleancode.ecommerce.adm.domain.voucher.Voucher;

public class CreateVoucherPromotionalImpl implements CreateVoucherPromotional{

	private final AdmRepository repository;

	public CreateVoucherPromotionalImpl(AdmRepository repository) {
		this.repository = repository;
	}

	@Override
	public void execute(String emailAdm, CreateVoucherDto dto) {
		Optional<Adm> adm = repository.findAdmByEmail(emailAdm);
		Voucher voucher = dto.create();
		adm.get().addVoucher(voucher);
	}
}
