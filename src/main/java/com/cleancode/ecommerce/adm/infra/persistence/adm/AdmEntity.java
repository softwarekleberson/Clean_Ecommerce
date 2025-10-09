package com.cleancode.ecommerce.adm.infra.persistence.adm;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_adm")
public class AdmEntity extends UserEntity {

}
