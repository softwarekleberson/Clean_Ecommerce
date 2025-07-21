package com.cleancode.ecommerce.product.application.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.cleancode.ecommerce.product.domain.Brand;
import com.cleancode.ecommerce.product.domain.Description;
import com.cleancode.ecommerce.product.domain.Dimension;
import com.cleancode.ecommerce.product.domain.Edition;
import com.cleancode.ecommerce.product.domain.Image;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.ProductCategory;
import com.cleancode.ecommerce.product.domain.books.Author;
import com.cleancode.ecommerce.product.domain.books.Book;
import com.cleancode.ecommerce.product.domain.books.CategoryBoock;
import com.cleancode.ecommerce.product.domain.books.Isbn;
import com.cleancode.ecommerce.product.domain.books.Page;
import com.cleancode.ecommerce.product.domain.books.Pricing;
import com.cleancode.ecommerce.product.domain.books.PublisherDate;
import com.cleancode.ecommerce.product.domain.books.Synopsis;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public class CreateBoockDto extends CreateProductDto {

	private String synopsis;
	private String page;
	private String author;
	private String edition;
	private String isbn;
	private CategoryBoock categoryBoock;
	private String height;
	private String width;
	private String length;
	private String weight;
	private LocalDate publisherDate;
	private BigDecimal pricing;

	public CreateBoockDto(String name, String description, BigDecimal price, TypeCoin typeCoin,
			ProductCategory category, String brand, List<Image> images, String synopsis, String page, String author,
			String edition, String isbn, CategoryBoock categoryBoock, String height, String width, String length,
			String weight, LocalDate publisherDate, BigDecimal pricing) {

		super(name, description, price, typeCoin, ProductCategory.BOOKS, brand, images);
		this.synopsis = synopsis;
		this.page = page;
		this.author = author;
		this.edition = edition;
		this.isbn = isbn;
		this.categoryBoock = categoryBoock;
		this.height = height;
		this.width = width;
		this.length = length;
		this.weight = weight;
		this.publisherDate = publisherDate;
		this.pricing = pricing;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public String getPage() {
		return page;
	}

	public String getAuthor() {
		return author;
	}

	public String getEdition() {
		return edition;
	}

	public String getIsbn() {
		return isbn;
	}

	public CategoryBoock getCategoryBoock() {
		return categoryBoock;
	}

	public String getHeight() {
		return height;
	}

	public String getWidth() {
		return width;
	}

	public String getLength() {
		return length;
	}

	public String getWeight() {
		return weight;
	}

	public LocalDate getPublisherDate() {
		return publisherDate;
	}

	@Override
	public Product toProduct() {
		return new Book(new Name(getName()), new Description(getDescription()), new Price(getPrice(), getTypeCoin()),
				getCategory(), new Brand(getBrand()), getImages(), new Synopsis(getSynopsis()), new Page(getPage()),
				new Author(getAuthor()), new Edition(getEdition()), new Isbn(getIsbn()), categoryBoock,
				new Dimension(height, width, length, weight), new PublisherDate(getPublisherDate()), new Pricing(pricing));
	}
}
