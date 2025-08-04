package com.cleancode.ecommerce.stock.infra.persistence;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "input")
public class ProductInputEntity {

	@Id
	private String id = UUID.randomUUID().toString();
	
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "product_quality")
	private ProductQualityEntity productQuality;
	
	@Column(name = "entry_time")
	private LocalDateTime entryTime = LocalDateTime.now();
	
	@Column(name = "purchase_price")
	private BigDecimal purchasePrice;
	
	private String supplier;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private StockEntity stock;
}