package com.cleancode.ecommerce.customer.shared.domain;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalCpfException;

public class Cpf {

	private String cpf;
	
	public Cpf(String cpf) {
		if(cpf == null) {
			throw new IllegalCpfException("Cpf invalid");
		}
		
		if(!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")) {
			throw new IllegalCpfException("Cpf needs this format xxx.xxx.xxx-xx");
		}
		
		this.cpf = cpf;
	}
	
	public String getCpf() {
		return cpf;
	}

	@Override
	public String toString() {
		return "Cpf [cpf=" + cpf + "]";
	}
}