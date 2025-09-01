package com.cleancode.ecommerce.payment.domain.card;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;

public class Card {

	private CustomerId customerId;
	private UUID id;
	private boolean main;
	private PrintedName printedName;
	private Code code;
	private NumberCard numberCard;
	private LocalDate expirationDate;
	private Flag flag;
	
	public Card(CustomerId customerId, UUID id, boolean main, PrintedName printedName, Code code, NumberCard numberCard,
			LocalDate expirationDate, Flag flag) {
		
		this.customerId = customerId;
		this.id = id;
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

	public void setCustomerId(CustomerId customerId) {
		this.customerId = customerId;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public boolean isMain() {
		return main;
	}

	public void setMain(boolean main) {
		this.main = main;
	}

	public PrintedName getPrintedName() {
		return printedName;
	}

	public void setPrintedName(PrintedName printedName) {
		this.printedName = printedName;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public NumberCard getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(NumberCard numberCard) {
		this.numberCard = numberCard;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Flag getFlag() {
		return flag;
	}

	public void setFlag(Flag flag) {
		this.flag = flag;
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
