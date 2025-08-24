package com.cleancode.ecommerce.product.domain;

public class ProductStatusPolicy {

	private String justification;
	private ProductStatusCategory category;

	public ProductStatusPolicy(String justification, ProductStatusCategory category) {
		this.justification = justification;
		this.category = category;
	}

	public ProductStatusPolicy(ProductStatusCategory category) {
		this.category = category;
	}

	public static ProductStatusPolicy manualDeactivation(String justification, ProductStatusCategory category) {
		return new ProductStatusPolicy(justification, category);
	}

	public static ProductStatusPolicy automaticDeactivation() {
		return new ProductStatusPolicy(ProductStatusCategory.OUT_OF_MARKET);
	}

	public static ProductStatusPolicy activation(String justification, ProductStatusCategory category) {
		return new ProductStatusPolicy(justification, category);
	}

	public String getJustification() {
		return justification;
	}

	public ProductStatusCategory getCategory() {
		return category;
	}
}