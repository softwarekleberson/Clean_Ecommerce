package com.cleancode.ecommerce.adm.infra.persistence.voucher;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class VoucherEntity {

	@Id
	@Column(name = "voucher_id", updatable = false, nullable = false)
	private String voucherId;
	
	private String message;
	private LocalDate emission;
	
	@Column(name = "type_voucher")
	@Enumerated(EnumType.STRING)
	private TypeVoucherEntity typeVoucher;
}
