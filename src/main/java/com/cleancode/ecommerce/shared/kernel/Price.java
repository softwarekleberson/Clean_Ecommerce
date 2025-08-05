package com.cleancode.ecommerce.shared.kernel;

import java.math.BigDecimal;
import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Price {

	public static final BigDecimal LOWEST_PRICE = BigDecimal.ZERO;
	private final BigDecimal price;
	private TypeCoin coin;
	
	public Price(BigDecimal price, TypeCoin coin) {
		if(price == null) {
			throw new IllegalDomainException("Price not be null");
		}
		
		if(price.compareTo(LOWEST_PRICE) < 0) {
			throw new IllegalDomainException("Price must not be less than or equal to 0");
		}
		
		this.price = price;
		this.coin = coin;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public TypeCoin getCoin() {
		return coin;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coin, price);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		return coin == other.coin && Objects.equals(price, other.price);
	}
}
