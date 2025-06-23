package com.cleancode.ecommerce.product.domain.product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.shared.kernel.Price;

public abstract class Product {

	protected final IdProduct idProduct;
	protected boolean active = false;
	protected Name name;
	protected Description description;
	protected Price price;
	protected final ProductCategory productCategory;
	protected final Brand brand;
	protected List<Image> image;
	protected final CreatedAt createdAt;
	protected UpdateAt updateAt;
	
	public Product(Name name, Description description, Price price,
			ProductCategory productCategory, Brand brand, List<Image> image) {
		
		this.idProduct = new IdProduct();
		this.name = name;
		this.description = description;
		this.price = price;
		this.productCategory = productCategory;
		this.brand = brand;
		this.image = image;
		this.createdAt = new CreatedAt();
	}
	
	private void update() {
		this.updateAt = UpdateAt.update();
	}
	
	public void updatePrice(BigDecimal newPrice, TypeCoin typeCoin) {
		if(newPrice == null) {
			throw new IllegalDomainException("New Price not ne null");
		}
		
		this.price = Price.updatePrice(newPrice, typeCoin);
		update();
	}

	public IdProduct getIdProduct() {
		return idProduct;
	}

	public boolean isActive() {
		return active;
	}

	public Name getName() {
		return name;
	}

	public Description getDescription() {
		return description;
	}

	public Price getPrice() {
		return price;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public Brand getBrand() {
		return brand;
	}

	public List<Image> getImage() {
		return Collections.unmodifiableList(this.image);
	}

	public CreatedAt getCreatedAt() {
		return createdAt;
	}

	public UpdateAt getUpdateAt() {
		return updateAt;
	}
}