package com.cleancode.ecommerce.customer.domain;

public class Name {

	private String name;
	
	public Name(String name) {
		if(name == null) {
			throw new IllegalArgumentException("Name not be null");
		}
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Name [name=" + name + "]";
	}
}
