package com.cleancode.ecommerce.payment.domain.card;

import com.cleancode.ecommerce.payment.domain.card.exception.IllegalCardException;

public class NumberCard {

	private String numberCard;

	public NumberCard(String numberCard) {

		int sum = 0;
		boolean toggle = false;

		for (int i = numberCard.length() - 1; i >= 0; i--) {
			int n = Integer.parseInt(numberCard.substring(i, i + 1));
			if (toggle) {
				n *= 2;
				if (n > 9) {
					n -= 9;
				}
			}
			sum += n;
			toggle = !toggle;
		}

		if (sum % 10 != 0) {
			throw new IllegalCardException("Number card required 16 numbers valid");
		}

		this.numberCard = numberCard;
	}

	public String getNumberCard() {
		return numberCard;
	}
}
