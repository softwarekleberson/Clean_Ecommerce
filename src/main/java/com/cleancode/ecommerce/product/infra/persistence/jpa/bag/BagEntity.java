package com.cleancode.ecommerce.product.infra.persistence.jpa.bag;

import com.cleancode.ecommerce.product.infra.persistence.jpa.product.ProductEntity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "bag")
@DiscriminatorValue("bag")
public class BagEntity extends ProductEntity{

	private double volume;
	private String color;
}
