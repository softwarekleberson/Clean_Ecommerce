package com.cleancode.ecommerce.adm.infra.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cleancode.ecommerce.adm.infra.persistence.voucher.VoucherEntity;

public interface VoucherJpa extends JpaRepository<VoucherEntity, String>{

    List<VoucherEntity> findByCustomerIdAndActiveTrue(String customerId);

    Optional<VoucherEntity> findByVoucherId(String voucherId);
}
