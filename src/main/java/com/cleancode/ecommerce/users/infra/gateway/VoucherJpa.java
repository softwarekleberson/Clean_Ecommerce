package com.cleancode.ecommerce.users.infra.gateway;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.users.infra.persistence.voucher.VoucherEntity;

public interface VoucherJpa extends JpaRepository<VoucherEntity, String>{

	Page<VoucherEntity> findByCustomerIdAndActiveTrue(String id, Pageable pageable);

    Optional<VoucherEntity> findByVoucherId(String voucherId);
}
