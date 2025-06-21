package com.cleancode.ecommerce.product.domain.product;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Image {

	private String url;
	
	public Image(String url) {
        String regex = "\\b((https?:\\/\\/)?(www\\.)?[\\w\\-]+\\.[\\w\\-]+(\\.[\\w\\-]+)?([\\/\\w\\-\\.\\?\\=\\&\\#]*)?)\\b";
        if(!url.matches(regex)) {
        	throw new IllegalDomainException("Url not be valid");
        }
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
}
