package com.cleancode.ecommerce.stock.infra.persistence;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "stock")
public class StockEntity {

	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "product_id")
	private String productId;
	
	@Column(name = "total_quantity")
	private int totalQuantity;
	
	@Column(name = "quantity_available")
	private int quantityAvailable;
	
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<ReservationEntity> reservations;
	
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<ProductInputEntity> inputs;
	
	@OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<ProductOutputEntity> outputs;
}