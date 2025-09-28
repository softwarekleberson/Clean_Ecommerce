package com.cleancode.ecommerce.adm.infra.persistence;

import jakarta.persistence.Column;
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
public abstract class UserEntity {
    
	@Column(name = "email", nullable = false, unique = true, length = 100)
	protected String email;
	
    @Column(name = "password", nullable = false, length = 255)
	protected String password;
}