package com.cleancode.ecommerce.product.domain.product;

import java.util.List;

import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.Name;

public class Product {

	private Id id;
	private Name name;
	private Description description;
	private Price price;
	private StockQuantity stockQuantity;
	private Category category;
	private Brand brand;
	private List<Image> image;
	private Status status;
	private CreatedAt createdAt;
	private UpdateAt updateAt;
}
