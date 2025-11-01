package com.cleancode.ecommerce.adm.infra.persistence.voucher;

import com.cleancode.ecommerce.adm.infra.persistence.adm.AdmEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_replacement")
public class ReplacementEntity extends VoucherEntity {

	@Column(name = "customer_id")
	private String customerId;

	private double discount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private AdmEntity adm;
}
