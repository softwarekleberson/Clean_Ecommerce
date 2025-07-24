package com.cleancode.ecommerce.product.domain;

import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Midia {

	private final String id;
	private final String url;
	private final String description;
	
	public Midia(String url, String description) {
        String regex = "\\b((https?:\\/\\/)?(www\\.)?[\\w\\-]+\\.[\\w\\-]+(\\.[\\w\\-]+)?([\\/\\w\\-\\.\\?\\=\\&\\#]*)?)\\b";
        if(!url.matches(regex)) {
        	throw new IllegalDomainException("Url not be valid");
        }
        
        if(description == null || description.isBlank()) {
        	throw new IllegalDomainException("Description not be valid");
        }
        
        this.id = UUID.randomUUID().toString();
		this.url = url;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getDescription() {
		return description;
	}
}