package com.cleancode.ecommerce.product.infra.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.cleancode.ecommerce.product.application.dto.output.MidiaOutputDto;
import com.cleancode.ecommerce.product.domain.Midia;

public class MidiaOutputMapper {

	public static MidiaOutputDto toOutputDto(Midia midia) {
		return new MidiaOutputDto(midia.getId(), midia.getUrl(), midia.getDescription());
	}

	public static List<MidiaOutputDto> toOutputDtoList(List<Midia> midias) {
		if (midias == null) {
			return List.of();
		}
		return midias.stream().map(MidiaOutputMapper::toOutputDto).collect(Collectors.toList());
	}
}
