package com.cleancode.ecommerce.product.domain.product.books;

import java.util.List;
import java.util.Objects;

import com.cleancode.ecommerce.product.domain.product.Brand;
import com.cleancode.ecommerce.product.domain.product.ProductCategory;
import com.cleancode.ecommerce.product.domain.product.Description;
import com.cleancode.ecommerce.product.domain.product.Dimension;
import com.cleancode.ecommerce.product.domain.product.Edition;
import com.cleancode.ecommerce.product.domain.product.Image;
import com.cleancode.ecommerce.product.domain.product.Product;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;

public class Book extends Product {

	private final Synopsis synopsis;
	private final Page page;
	private final Author author;
	private final Edition edition;
	private final Isbn isbn;
	private final CategoryBoock categoryBoock;
	private final Dimension dimension;
	private final PublisherDate publisherDate;

	public Book(Name name, Description description, Price price, ProductCategory category, Brand brand, List<Image> image,
			Synopsis synopsis, Page page, Author author, Edition edition, Isbn isbn,
			CategoryBoock categoryBoock, Dimension dimension, PublisherDate publisherDate) {
		
		super(name, description, price, category, brand, image);
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

	@Override
	public int hashCode() {
		return Objects.hash(author, categoryBoock, dimension, edition, isbn, page, publisherDate, synopsis);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && categoryBoock == other.categoryBoock
				&& Objects.equals(dimension, other.dimension) && Objects.equals(edition, other.edition)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(page, other.page)
				&& Objects.equals(publisherDate, other.publisherDate) && Objects.equals(synopsis, other.synopsis);
	}
}
