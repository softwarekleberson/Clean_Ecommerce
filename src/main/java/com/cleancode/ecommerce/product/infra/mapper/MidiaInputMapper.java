package com.cleancode.ecommerce.product.infra.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.product.domain.Midia;
import com.cleancode.ecommerce.product.infra.persistence.jpa.product.MidiaEntity;

public class MidiaInputMapper {

	public static List<Midia> toMidiaList(List<MidiaEntity> entities) {
		if (entities == null)
			return Collections.emptyList();

		return entities.stream().map(img -> new Midia(img.getId(), img.getUrl(), img.getDescription()))
				.collect(Collectors.toList());
	}
}
