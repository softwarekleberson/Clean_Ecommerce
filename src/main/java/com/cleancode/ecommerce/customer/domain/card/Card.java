package com.cleancode.ecommerce.customer.domain.card;

import java.util.Objects;

public class Card {

	private CardId cardId;
	private boolean main;
	private PrintedName printedName;
	private Code code;
	private NumberCard numberCard;
	private ExpirationDate expirationDate;
	private Flag flag;

	public Card(boolean main, PrintedName printedName, Code code, NumberCard numberCard, ExpirationDate expirationDate,
			Flag flag) {

		this.cardId = new CardId();
		this.main = main;
		this.printedName = printedName;
		this.code = code;
		this.numberCard = numberCard;
		this.expirationDate = expirationDate;
		this.flag = flag;
	}

	public CardId getCardId() {
		return cardId;
	}

	public boolean isMain() {
		return main;
	}

	public PrintedName getPrintedName() {
		return printedName;
	}

	public Code getCode() {
		return code;
	}

	public NumberCard getNumberCard() {
		return numberCard;
	}

	public ExpirationDate getExpirationDate() {
		return expirationDate;
	}

	public Flag getFlag() {
		return flag;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardId, code, expirationDate, flag, main, numberCard, printedName);
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
		return Objects.equals(cardId, other.cardId) && Objects.equals(code, other.code)
				&& Objects.equals(expirationDate, other.expirationDate) && flag == other.flag && main == other.main
				&& Objects.equals(numberCard, other.numberCard) && Objects.equals(printedName, other.printedName);
	}
}
