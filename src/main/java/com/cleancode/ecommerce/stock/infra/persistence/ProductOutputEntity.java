package com.cleancode.ecommerce.stock.infra.persistence;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "output")
public class ProductOutputEntity {

	@Id
	private String id = UUID.randomUUID().toString();
	
	//Order = Pedido
	@Column(name = "orders_id")
	private String orderId;
	
	@Column(name = "product_id")
	private String productId;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private StockEntity stock;
}