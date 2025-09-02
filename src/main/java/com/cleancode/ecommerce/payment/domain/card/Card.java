package com.cleancode.ecommerce.payment.domain.card;

import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;

public class Card {

	private CustomerId customerId;
	private CardId id;
	private boolean main;
	private PrintedName printedName;
	private Code code;
	private NumberCard numberCard;
	private ExpirationDate expirationDate;
	private Flag flag;

	public Card(CustomerId customerId, boolean main, PrintedName printedName, Code code,
			NumberCard numberCard, ExpirationDate expirationDate, Flag flag) {

		this.id = new CardId();
		this.customerId = customerId;
		this.main = main;
		this.printedName = printedName;
		this.code = code;
		this.numberCard = numberCard;
		this.expirationDate = expirationDate;
		this.flag = flag;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public CardId getId() {
		return id;
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
		return Objects.hash(code, customerId, expirationDate, flag, main, numberCard, printedName);
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
		return Objects.equals(code, other.code) && Objects.equals(customerId, other.customerId)
				&& Objects.equals(expirationDate, other.expirationDate) && flag == other.flag && main == other.main
				&& Objects.equals(numberCard, other.numberCard) && Objects.equals(printedName, other.printedName);
	}
}
