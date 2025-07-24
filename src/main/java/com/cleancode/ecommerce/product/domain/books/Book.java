package com.cleancode.ecommerce.product.domain.books;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.cleancode.ecommerce.product.domain.Brand;
import com.cleancode.ecommerce.product.domain.Description;
import com.cleancode.ecommerce.product.domain.Dimension;
import com.cleancode.ecommerce.product.domain.Edition;
import com.cleancode.ecommerce.product.domain.Midia;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.ProductCategory;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public class Book extends Product {

	private final Synopsis synopsis;
	private final Page page;
	private final Author author;
	private final Edition edition;
	private final Isbn isbn;
	private final CategoryBook categoryBook;
	private final Dimension dimension;
	private final PublisherDate publisherDate;

	public Book(Name name, Description description, Price price, ProductCategory category, Brand brand,
			List<Midia> image, Synopsis synopsis, Page page, Author author, Edition edition, Isbn isbn,
			CategoryBook categoryBook, Dimension dimension, PublisherDate publisherDate) {

		super(name, description, price, category, brand, image);
		this.synopsis = synopsis;
		this.page = page;
		this.author = author;
		this.edition = edition;
		this.isbn = isbn;
		this.categoryBook = categoryBook;
		this.dimension = dimension;
		this.publisherDate = publisherDate;
	}
	
	@Override
	public void newPrice(BigDecimal newPrice, TypeCoin coin) {
		this.price = new Price(newPrice, coin);		
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

	public CategoryBook getCategoryBook() {
		return categoryBook;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public PublisherDate getPublisherDate() {
		return publisherDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, categoryBook, dimension, edition, isbn, page, publisherDate, synopsis);
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
		return Objects.equals(author, other.author) && categoryBook == other.categoryBook
				&& Objects.equals(dimension, other.dimension) && Objects.equals(edition, other.edition)
				&& Objects.equals(isbn, other.isbn) && Objects.equals(page, other.page)
				&& Objects.equals(publisherDate, other.publisherDate) && Objects.equals(synopsis, other.synopsis);
	}
}