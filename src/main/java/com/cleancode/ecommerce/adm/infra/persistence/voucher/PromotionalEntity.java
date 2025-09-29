package com.cleancode.ecommerce.adm.infra.persistence.voucher;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_promotional")
public class PromotionalEntity extends VoucherEntity {

	private String codeVoucher;
	private int discount;
	private LocalDate validity;
}
