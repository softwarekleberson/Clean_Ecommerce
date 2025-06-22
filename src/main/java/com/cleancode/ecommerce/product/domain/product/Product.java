package com.cleancode.ecommerce.product.domain.product;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.Name;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public abstract class Product {

	protected final Id id;
	protected boolean active = false;
	protected Name name;
	protected Description description;
	protected Price price;
	protected StockQuantity stockQuantity;
	protected final Category category;
	protected final Brand brand;
	protected List<Image> image;
	protected final CreatedAt createdAt;
	protected UpdateAt updateAt;
	
	public Product(Id id, boolean active, Name name, Description description, Price price, StockQuantity stockQuantity,
			Category category, Brand brand, List<Image> image, CreatedAt createdAt, UpdateAt updateAt) {
		
		this.id = id;
		this.active = active;
		this.name = name;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.category = category;
		this.brand = brand;
		this.image = image;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
	
	private void update() {
		this.updateAt = UpdateAt.update();
	}
	
	public void updatePrice(BigDecimal newPrice) {
		if(newPrice == null) {
			throw new IllegalDomainException("New Price not ne null");
		}
		this.price = Price.updatePrice(newPrice);
		update();
	}
	
	public void updateStockQuantity(int newStockQuantity) {
		if(newStockQuantity < 0) {
			throw new IllegalDomainException("Stock quantity not be less than 0");
		}
		this.stockQuantity = StockQuantity.updateStockQuantity(newStockQuantity);
		update();
	}

	public Id getId() {
		return id;
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

	public StockQuantity getStockQuantity() {
		return stockQuantity;
	}

	public Category getCategory() {
		return category;
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