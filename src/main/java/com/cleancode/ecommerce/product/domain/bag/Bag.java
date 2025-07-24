package com.cleancode.ecommerce.product.domain.bag;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.cleancode.ecommerce.product.domain.Brand;
import com.cleancode.ecommerce.product.domain.Description;
import com.cleancode.ecommerce.product.domain.Midia;
import com.cleancode.ecommerce.product.domain.Product;
import com.cleancode.ecommerce.product.domain.ProductCategory;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;

public class Bag extends Product {

	private Volume volume;
	private Color color;

	public Bag(Name name, Description description, Price price, ProductCategory category, Brand brand,
			List<Midia> image, Volume volume, Color color) {
		super(name, description, price, category, brand, image);
		this.volume = volume;
		this.color = color;
	}

	@Override
	public void newPrice(BigDecimal newPrice, TypeCoin coin) {
		this.price = new Price(newPrice, coin);
	}

	public Volume getVolume() {
		return volume;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bag other = (Bag) obj;
		return Objects.equals(color, other.color) && Objects.equals(volume, other.volume);
	}
}