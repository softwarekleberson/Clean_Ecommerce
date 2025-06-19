package com.cleancode.ecommerce.customer.domain;

import java.time.LocalDate;

public class Birth {

	private LocalDate birth;
	
	public Birth(LocalDate birth) {
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