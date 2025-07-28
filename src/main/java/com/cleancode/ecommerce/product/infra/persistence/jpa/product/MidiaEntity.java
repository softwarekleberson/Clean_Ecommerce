package com.cleancode.ecommerce.product.infra.persistence.jpa.product;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "midia")
public class MidiaEntity {
	
	@Id
	private String id = UUID.randomUUID().toString();
	private String url;
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;
}