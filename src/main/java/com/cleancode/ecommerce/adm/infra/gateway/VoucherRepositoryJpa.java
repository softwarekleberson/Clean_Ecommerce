package com.cleancode.ecommerce.adm.infra.gateway;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cleancode.ecommerce.adm.domain.voucher.Voucher;
import com.cleancode.ecommerce.adm.domain.voucher.repository.VoucherRepository;
import com.cleancode.ecommerce.adm.infra.mapper.VoucherMapper;
import com.cleancode.ecommerce.adm.infra.persistence.voucher.VoucherEntity;

@Repository
public class VoucherRepositoryJpa implements VoucherRepository {

	private final VoucherJpa jpa;
	
	public VoucherRepositoryJpa(VoucherJpa jpa) {
		this.jpa = jpa;
	}
	
	@Override
	public void save(Voucher voucher) {
		VoucherEntity entity = VoucherMapper.toEntity(voucher);
		jpa.save(entity);
	}

	@Override
	public List<Voucher> listAllVoucher(String customerId) {
		List<VoucherEntity> entities = jpa.findByCustomerId(customerId);

	    return entities.stream()
	                   .map(VoucherMapper::toDomain)
	                   .toList();
	}
}
