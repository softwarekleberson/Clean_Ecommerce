package com.cleancode.ecommerce.stock.infra.persistence;

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
@Table(name = "reservation")
public class ReservationEntity {

	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "cart_id")
	private String cartId;
	
	@Column(name = "customer_id")
	private String customerId;
	
	private int quantity;
	
	@Column(name = "reservation_time")
	private LocalDateTime reservationTime;
	
	@Column(name = "reserve_status")
	@Enumerated(EnumType.STRING)
	private ReserveStatusEntity reserveStatus;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private StockEntity stock;
}