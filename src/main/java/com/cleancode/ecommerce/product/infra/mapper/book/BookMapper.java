package com.cleancode.ecommerce.product.infra.mapper.book;

import java.util.List;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.product.domain.books.Book;
import com.cleancode.ecommerce.product.domain.books.CategoryBook;
import com.cleancode.ecommerce.product.infra.persistence.jpa.book.BookEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.MidiaEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductCategoryEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.TypeCoinEntity;

public class BookMapper {

	public static Book toDomain(BookEntity entity) {
		return null;
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
		entity.setMidia(imageEntities);
		
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
		entity.setPrice(domain.getPrice().getPrice());

		return entity;
	}
}