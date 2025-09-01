package com.cleancode.ecommerce.customer.domain.card;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Card {

	private UUID id;
	private String printedName;
	private String code;
	private String numberCard;
	private LocalDate expirationDate;
	private Flag flag;
	private Type type;
	
	public Card(String printedName, String code, String numberCard, LocalDate expirationDate, Flag flag,
			Type type) {
		
		this.id = UUID.randomUUID();
		this.printedName = printedName;
		this.code = code;
		this.numberCard = numberCard;
		this.expirationDate = expirationDate;
		this.flag = flag;
		this.type = type;
	}

	public UUID getId() {
		return id;
	}

	public String getPrintedName() {
		return printedName;
	}

	public String getCode() {
		return code;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public Flag getFlag() {
		return flag;
	}

	public Type getType() {
		return type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, expirationDate, flag, id, numberCard, printedName, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		return Objects.equals(code, other.code) && Objects.equals(expirationDate, other.expirationDate)
				&& flag == other.flag && Objects.equals(id, other.id) && Objects.equals(numberCard, other.numberCard)
				&& Objects.equals(printedName, other.printedName) && type == other.type;
	}
}
