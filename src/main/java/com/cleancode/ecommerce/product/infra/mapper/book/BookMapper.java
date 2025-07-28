package com.cleancode.ecommerce.product.infra.mapper.book;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.product.domain.Brand;
import com.cleancode.ecommerce.product.domain.CreatedAt;
import com.cleancode.ecommerce.product.domain.Description;
import com.cleancode.ecommerce.product.domain.Dimension;
import com.cleancode.ecommerce.product.domain.IdProduct;
import com.cleancode.ecommerce.product.domain.Midia;
import com.cleancode.ecommerce.product.domain.Pricing;
import com.cleancode.ecommerce.product.domain.ProductCategory;
import com.cleancode.ecommerce.product.domain.UpdateAt;
import com.cleancode.ecommerce.product.domain.books.Author;
import com.cleancode.ecommerce.product.domain.books.Book;
import com.cleancode.ecommerce.product.domain.books.CategoryBook;
import com.cleancode.ecommerce.product.domain.books.Edition;
import com.cleancode.ecommerce.product.domain.books.Isbn;
import com.cleancode.ecommerce.product.domain.books.Page;
import com.cleancode.ecommerce.product.domain.books.PublisherDate;
import com.cleancode.ecommerce.product.domain.books.Synopsis;
import com.cleancode.ecommerce.product.infra.persistence.jpa.book.BookEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.MidiaEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductCategoryEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.TypeCoinEntity;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public class BookMapper {

	public static Book toDomain(BookEntity entity) {
		return new Book(new IdProduct(entity.getId()), entity.isActive(), new Name(entity.getName()),
				new Description(entity.getDescription()),
				new Price(entity.getPrice(), TypeCoin.valueOf(entity.getTypeCoin().name())),
				ProductCategory.valueOf(entity.getCategory().name()), new Brand(entity.getBrand()),
				toMidiaList(entity.getMidias()), new Pricing(entity.getPricing()) ,new CreatedAt(entity.getCreatedAt()),
				new UpdateAt(entity.getUpdateAt()), new Synopsis(entity.getSynopsis()), new Page(entity.getPage()),
				new Author(entity.getAuthor()), new Edition(entity.getEdition()), new Isbn(entity.getIsbn()),
				CategoryBook.valueOf(entity.getCategoryBook().name()),
				new Dimension(entity.getHeight(), entity.getWidth(), entity.getLength(), entity.getWeight()),
				new PublisherDate(entity.getPublisherDate()));
	}

	public static BookEntity toEntity(Book domain) {
		BookEntity entity = new BookEntity();

		entity.setId(domain.getIdProduct().getIdProduct());
		entity.setActive(domain.isActive());
		entity.setName(domain.getName().getName());
		entity.setDescription(domain.getDescription().getDescription());
		entity.setPrice(domain.getPrice().getPrice());
		entity.setTypeCoin(TypeCoinEntity.valueOf(domain.getPrice().getCoin().name()));
		entity.setCategory(ProductCategoryEntity.valueOf(domain.getProductCategory().name()));
		entity.setBrand(domain.getBrand().getBrand());
		entity.setCreatedAt(domain.getCreatedAt().getCreatedAt());
		entity.setUpdateAt(domain.getUpdateAt().getUpdateAt());

		List<MidiaEntity> imageEntities = domain.getMidia().stream().map(midia -> {
			MidiaEntity imageEntity = new MidiaEntity();
			imageEntity.setUrl(midia.getUrl());
			imageEntity.setDescription(midia.getDescription());
			imageEntity.setProduct(entity);
			return imageEntity;
		}).collect(Collectors.toList());
		entity.setMidias(imageEntities);

		entity.setPricing(domain.getPricing().getPricing());
		entity.setSynopsis(domain.getSynopsis().getSynopsis());
		entity.setPage(domain.getPage().getPage());
		entity.setEdition(domain.getEdition().getEdition());
		entity.setAuthor(domain.getAuthor().getAuthor());
		entity.setIsbn(domain.getIsbn().getIsbn());
		entity.setCategoryBook(CategoryBook.valueOf(domain.getCategoryBook().name()));
		entity.setHeight(domain.getDimension().getHeight());
		entity.setWidth(domain.getDimension().getWidth());
		entity.setLength(domain.getDimension().getLength());
		entity.setWeight(domain.getDimension().getWeight());
		entity.setPublisherDate(domain.getPublisherDate().getPublisherDate());

		return entity;
	}

	private static List<Midia> toMidiaList(List<MidiaEntity> entities) {
		if (entities == null)
			return Collections.emptyList();

		return entities.stream().map(img -> new Midia(img.getId(), img.getUrl(), img.getDescription()))
				.collect(Collectors.toList());
	}

}