package com.cleancode.ecommerce.adm.infra.persistence.adm;

import java.util.HashMap;
import java.util.Map;

import com.cleancode.ecommerce.adm.infra.persistence.voucher.ReplacementEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_adm")
public class AdmEntity extends UserEntity {

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	@MapKey(name = "voucherId") 
	private Map<String, ReplacementEntity> replacements = new HashMap<>();;

}
