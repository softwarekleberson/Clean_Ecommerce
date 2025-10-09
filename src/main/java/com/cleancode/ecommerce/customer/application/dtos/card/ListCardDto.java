package com.cleancode.ecommerce.customer.application.dtos.card;

import java.math.BigDecimal;

import com.cleancode.ecommerce.customer.domain.card.Card;

public record ListCardDto(String cardId, boolean main, String printedName, String code, BigDecimal credit) {

	public ListCardDto(Card card) {
		this(card.getCardId().getCardId(), card.isMain(), card.getPrintedName().getName(), card.getCode().getCode(), card.getCredit().getCredit());
	}
}
