package com.cleancode.ecommerce.payment.domain.card;

import java.util.UUID;

public class CardId {

	private final String cardId;
	
	public CardId() {
		this.cardId = UUID.randomUUID().toString();
	}
	
	public CardId(String cardId) {
		this.cardId = cardId;
	}
	
	public String getCardId() {
		return cardId;
	}
}
