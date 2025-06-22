package com.cleancode.ecommerce.product.domain.product.books;

import java.util.List;

import com.cleancode.ecommerce.customer.domain.customer.Id;
import com.cleancode.ecommerce.customer.domain.customer.Name;
import com.cleancode.ecommerce.product.domain.product.Brand;
import com.cleancode.ecommerce.product.domain.product.Category;
import com.cleancode.ecommerce.product.domain.product.CreatedAt;
import com.cleancode.ecommerce.product.domain.product.Description;
import com.cleancode.ecommerce.product.domain.product.Dimension;
import com.cleancode.ecommerce.product.domain.product.Edition;
import com.cleancode.ecommerce.product.domain.product.Image;
import com.cleancode.ecommerce.product.domain.product.Price;
import com.cleancode.ecommerce.product.domain.product.Product;
import com.cleancode.ecommerce.product.domain.product.StockQuantity;
import com.cleancode.ecommerce.product.domain.product.UpdateAt;

public class Book extends Product {

	private final Synopsis synopsis;
	private final Page page;
	private final Author author;
	private final Edition edition;
	private final Isbn isbn;
	private final CategoryBoock categoryBoock;
	private final Dimension dimension;
	private final PublisherDate publisherDate;

	public Book(Id id, boolean active, Name name, Description description, Price price, StockQuantity stockQuantity,
			Category category, Brand brand, List<Image> image, CreatedAt createdAt, UpdateAt updateAt,
			Synopsis synopsis, Page page, Author author, Edition edition, Isbn isbn,
			CategoryBoock categoryBoock, Dimension dimension, PublisherDate publisherDate) {
		
		super(id, active, name, description, price, stockQuantity, category, brand, image, createdAt, updateAt);
		this.synopsis = synopsis;
		this.page = page;
		this.author = author;
		this.edition = edition;
		this.isbn = isbn;
		this.categoryBoock = categoryBoock;
		this.dimension = dimension;
		this.publisherDate = publisherDate;
	}

	public Synopsis getSynopsis() {
		return synopsis;
	}

	public Page getPage() {
		return page;
	}

	public Author getAuthor() {
		return author;
	}

	public Edition getEdition() {
		return edition;
	}

	public Isbn getIsbn() {
		return isbn;
	}

	public CategoryBoock getCategoryBoock() {
		return categoryBoock;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public PublisherDate getPublisherDate() {
		return publisherDate;
	}
}
