package com.cleancode.ecommerce.product.infra.persistence.jpa.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
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
@Table(name = "product")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProductEntity {

	@Id
	protected String id = UUID.randomUUID().toString();
	protected boolean active;
	protected String name;
	protected String description;
	protected BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type_coin")
	protected TypeCoinEntity typeCoin;

	@Enumerated(EnumType.STRING)
	protected ProductCategoryEntity category;
	protected String brand;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	protected List<ImageEntity> image;
	
	@Column(name = "created_at")
	protected LocalDateTime createdAt;
	
	@Column(name = "update_at")
	protected LocalDateTime updateAt;
}