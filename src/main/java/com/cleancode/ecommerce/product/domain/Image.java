package com.cleancode.ecommerce.product.domain;

import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Image {

	private String id;
	private String url;
	
	public Image(String url) {
        String regex = "\\b((https?:\\/\\/)?(www\\.)?[\\w\\-]+\\.[\\w\\-]+(\\.[\\w\\-]+)?([\\/\\w\\-\\.\\?\\=\\&\\#]*)?)\\b";
        if(!url.matches(regex)) {
        	throw new IllegalDomainException("Url not be valid");
        }
        
        this.id = UUID.randomUUID().toString();
		this.url = url;
	}
	
	public String getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}
}