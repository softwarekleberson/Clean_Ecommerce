package com.cleancode.ecommerce.product.domain.bag;

import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

public class Volume {

	public final double MIN_VOLUME_ACCEPTABLE = 0;
	private double volume;

	public Volume(double volume) {
		if (volume <= MIN_VOLUME_ACCEPTABLE) {
			throw new IllegalDomainException("Volume not be null");
		}

		this.volume = volume;
	}

	public double getVolume() {
		return volume;
	}

	@Override
	public int hashCode() {
		return Objects.hash(MIN_VOLUME_ACCEPTABLE, volume);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Volume other = (Volume) obj;
		return Double.doubleToLongBits(MIN_VOLUME_ACCEPTABLE) == Double.doubleToLongBits(other.MIN_VOLUME_ACCEPTABLE)
				&& Double.doubleToLongBits(volume) == Double.doubleToLongBits(other.volume);
	}
}
