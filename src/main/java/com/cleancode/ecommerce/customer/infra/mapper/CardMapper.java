package com.cleancode.ecommerce.customer.infra.mapper;

import com.cleancode.ecommerce.customer.domain.card.Card;
import com.cleancode.ecommerce.customer.domain.card.Code;
import com.cleancode.ecommerce.customer.domain.card.ExpirationDate;
import com.cleancode.ecommerce.customer.domain.card.Flag;
import com.cleancode.ecommerce.customer.domain.card.NumberCard;
import com.cleancode.ecommerce.customer.domain.card.PrintedName;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.card.CardEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.card.FlagEntity;
import com.cleancode.ecommerce.customer.infra.persistence.jpa.customer.CustomerEntity;

public class CardMapper {

	private CardMapper() {
	}

	public static CardEntity toEntity(Card card, CustomerEntity customerEntity) {
		if (card == null) {
			return null;
		}

		CardEntity entity = new CardEntity();
		entity.setCard_id(card.getCardId().getCardId().toString());
		entity.setMain(card.isMain());
		entity.setPrinted_name(card.getPrintedName().getName());
		entity.setCode(card.getCode().getCode());
		entity.setNumber_card(card.getNumberCard().getNumberCard());
		entity.setExpiration_date(card.getExpirationDate().getExpirationDate());
		entity.setFlag(FlagEntity.valueOf(card.getFlag().name()));
		entity.setCustomer(customerEntity);

		return entity;
	}

	public static void updateEntity(Card card, CardEntity entity) {
	
		entity.setCard_id(card.getCardId().getCardId().toString());
		entity.setMain(card.isMain());
		entity.setPrinted_name(card.getPrintedName().getName());
		entity.setCode(card.getCode().getCode());
		entity.setNumber_card(card.getNumberCard().getNumberCard());
		entity.setExpiration_date(card.getExpirationDate().getExpirationDate());
		entity.setFlag(FlagEntity.valueOf(card.getFlag().name()));
	}
	
	public static Card toDomain(CardEntity entity) {
		if (entity == null) {
			return null;
		}

		return new
				Card(entity.isMain(),
				new PrintedName(entity.getPrinted_name()),
				new Code(entity.getCode()),
				new NumberCard(entity.getNumber_card()),
				new ExpirationDate(entity.getExpiration_date()),
				Flag.valueOf(entity.getFlag().name()));
	}
}
