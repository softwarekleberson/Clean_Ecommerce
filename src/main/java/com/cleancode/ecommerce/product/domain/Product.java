package com.cleancode.ecommerce.product.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.shared.kernel.Price;

public abstract class Product {

	protected final IdProduct idProduct;
	protected boolean active = false;
	protected Name name;
	protected Description description;
	protected Price price;
	protected final ProductCategory category;
	protected final Brand brand;
	protected List<Image> image = new LinkedList<>();
	protected final CreatedAt createdAt;
	protected UpdateAt updateAt;

	public Product(Name name, Description description, Price price, ProductCategory category, Brand brand,
			List<Image> image) {

		this.idProduct = new IdProduct();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.brand = brand;
		this.image = image;
		this.createdAt = new CreatedAt();
	}

	private void update() {
		this.updateAt = UpdateAt.update();
	}

	public void updateProduct(String newDescription, String newName, BigDecimal newPrice, TypeCoin typeCoin) {

		if (newName != null && !newName.isBlank()) {
			this.name = new Name(newName);
			update();
		}

		if (newPrice != null) {
			this.price = new Price(newPrice, typeCoin);
			update();
		}

		if (newDescription != null && !newDescription.isBlank()) {
			this.description = new Description(newDescription);
			update();
		}
	}
	
	public void updateImage(String id, String url) {
		this.image.removeIf(i -> i.getId().equals(id));
		this.image.add(new Image(url));
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