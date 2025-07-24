package com.cleancode.ecommerce.product.infra.mapper.bag;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.product.domain.Brand;
import com.cleancode.ecommerce.product.domain.Description;
import com.cleancode.ecommerce.product.domain.Midia;
import com.cleancode.ecommerce.product.domain.ProductCategory;
import com.cleancode.ecommerce.product.domain.bag.Bag;
import com.cleancode.ecommerce.product.domain.bag.Color;
import com.cleancode.ecommerce.product.domain.bag.Volume;
import com.cleancode.ecommerce.product.infra.persistence.jpa.bag.BagEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.MidiaEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductCategoryEntity;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.TypeCoinEntity;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public class BagMapper {

	private BagMapper() {
	}

	public static BagEntity toEntity(Bag domain) {
		BagEntity entity = new BagEntity();

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

		entity.setVolume(domain.getVolume().getVolume());
		entity.setColor(domain.getColor().getColor());

		return entity;
	}

	public static Bag toDomain(BagEntity entity) {
		return new Bag(new Name(entity.getName()), new Description(entity.getDescription()),
				new Price(entity.getPrice(), TypeCoin.valueOf(entity.getTypeCoin().name())),
				ProductCategory.valueOf(entity.getCategory().name()), new Brand(entity.getBrand()),
				toImageList(entity.getMidia()), new Volume(entity.getVolume()), new Color(entity.getColor()));
	}

	private static List<Midia> toImageList(List<MidiaEntity> entities) {
		if (entities == null)
			return Collections.emptyList();
		return entities.stream().map(img -> new Midia(img.getUrl(), img.getDescription())).collect(Collectors.toList());
	}
}