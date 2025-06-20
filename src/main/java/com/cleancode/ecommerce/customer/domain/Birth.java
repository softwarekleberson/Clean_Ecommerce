package com.cleancode.ecommerce.customer.domain;

import java.time.LocalDate;

import com.cleancode.ecommerce.customer.domain.exception.IllegalDomainException;

public class Birth {

	private LocalDate birth;
	
	public Birth(LocalDate birth) {
		if(birth == null || birth.isAfter(LocalDate.now().minusYears(18)))
			throw new IllegalDomainException("For register you need 18 years");
			
		this.birth = birth;
	}
	
	public LocalDate getBirth() {
		return birth;
	}

	@Override
	public String toString() {
		return "Birth [birth=" + birth + "]";
	}
}