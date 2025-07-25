package com.cleancode.ecommerce.product.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
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
	protected final ProductCategory category;
	protected final Brand brand;
	protected List<Midia> midias = new LinkedList<>();
	protected final CreatedAt createdAt;
	protected UpdateAt updateAt;

	public Product(Name name, Description description, Price price, ProductCategory category, Brand brand,
			List<Midia> midias) {

		this.idProduct = new IdProduct();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.brand = brand;
		this.midias = midias;
		this.createdAt = new CreatedAt();
		this.updateAt = new UpdateAt();
	}
	
	public Product(IdProduct idProduct, boolean active, Name name, Description description, Price price,
			ProductCategory category, Brand brand, List<Midia> midias, CreatedAt createdAt, UpdateAt updateAt) {
		
		this.idProduct = idProduct;
		this.active = active;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.brand = brand;
		this.midias = midias;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}

	public void activate() {
		this.active = true;
		registerChange();
	}
	
	public void deactivate() {
		this.active = false;
		registerChange();
	}

	private void registerChange() {
		this.updateAt = UpdateAt.update();
	}

	public void reviseDetails(String newDescription, String newName, BigDecimal newPrice, TypeCoin typeCoin) {

		if (newName != null && !newName.isBlank()) {
			this.name = new Name(newName);
		}

		if (newPrice != null) {
			this.price = new Price(newPrice, typeCoin);
			registerChange();
		}

		if (newDescription != null && !newDescription.isBlank()) {
			this.description = new Description(newDescription);
			registerChange();
		}
	}

	public void replaceMedia(String id, String url, String description) {
		boolean idExist = this.midias.stream().anyMatch(i -> i.getId().equals(id));

		if (!idExist) {
			throw new IllegalDomainException("Midia with ID '" + id + "' not found.");
		}

		this.midias.removeIf(i -> i.getId().equals(id));
		this.midias.add(new Midia(url, description));
		registerChange();
	}

	public void removeMediaById(String id) {
		boolean idExist = this.midias.stream().anyMatch(i -> i.getId().equals(id));

		if (!idExist) {
			throw new IllegalDomainException("Midia with ID '" + id + "' not found.");
		}

		this.midias.removeIf(m -> m.getId().equals(id));
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

	public List<Midia> getMidia() {
		return Collections.unmodifiableList(this.midias);
	}

	public CreatedAt getCreatedAt() {
		return createdAt;
	}

	public UpdateAt getUpdateAt() {
		return updateAt;
	}

	@Override
	public String toString() {
		return "Product [idProduct=" + idProduct + ", active=" + active + ", name=" + name + ", description="
				+ description + ", price=" + price + ", category=" + category + ", brand=" + brand + ", midia=" + midias
				+ ", createdAt=" + createdAt + ", updateAt=" + updateAt + "]";
	}
}