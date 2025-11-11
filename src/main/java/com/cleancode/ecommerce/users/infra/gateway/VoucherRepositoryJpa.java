package com.cleancode.ecommerce.users.infra.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cleancode.ecommerce.users.domain.voucher.Voucher;
import com.cleancode.ecommerce.users.domain.voucher.repository.VoucherRepository;
import com.cleancode.ecommerce.users.infra.mapper.VoucherMapper;
import com.cleancode.ecommerce.users.infra.persistence.voucher.VoucherEntity;

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
	public List<Voucher> listAllVoucher(String email) {
		List<VoucherEntity> entities = jpa.findByCustomerIdAndActiveTrue(email);

		return entities.stream().map(VoucherMapper::toDomain).toList();
	}

	@Override
	public Optional<Voucher> listSingleVoucher(String voucherId) {
	    return jpa.findByVoucherId(voucherId)
	              .map(VoucherMapper::toDomain);
	}
}
