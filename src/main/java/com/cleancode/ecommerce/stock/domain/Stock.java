package com.cleancode.ecommerce.stock.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.IdProduct;

public class Stock {

	public static final int MIN_QUANTITY = 0;

	private IdStock id;
	private IdProduct productId;
	private int totalQuantity;
	private int quantityAvailable;
	private List<Reservations> reservations = new ArrayList<>();
	private List<ProductInput> productInputs = new ArrayList<>();
	private List<ProductOutput> productOutputs = new ArrayList<>();

	public Stock(IdProduct productId, int totalQuantity) {
		this.id = new IdStock();
		this.productId = productId;
		this.totalQuantity = totalQuantity;
		this.quantityAvailable = totalQuantity;
	}

	public void productInput(int quantity, ProductQuality productQuality, BigDecimal purchasePrice, String supplier) {

		if (quantity <= MIN_QUANTITY) {
			throw new IllegalDomainException("quntity must be positive");
		}

		this.totalQuantity += quantity;
		this.quantityAvailable += quantity;
		ProductInput entryMovement = new ProductInput(quantity, productQuality, purchasePrice, supplier);
		this.productInputs.add(entryMovement);
	}

	public Reservations reservation(String cartId, String customerId, int quantity) {
		if (quantity > this.quantityAvailable) {
			throw new IllegalDomainException("Insufficient stock");
		}

		Reservations reservation = new Reservations(UUID.randomUUID().toString() ,customerId, cartId, quantity);
		this.quantityAvailable -= quantity;
		this.reservations.add(reservation);
		return reservation;
	}

	public void cancelReservation(String reservationId) {
		Reservations reservation = reservations.stream().filter(r -> r.getId().equals(reservationId))
				.findFirst().orElseThrow(() -> new IllegalDomainException("Reservation not found"));

		reservation.cancel();
		this.quantityAvailable += reservation.getQuantity().getQuantity();
	}

	public void confirmOrder(String orderId, String reservationId) {
		Reservations reservation = reservations.stream()

				.filter(r -> r.getId().equals(reservationId)).findFirst()
				.orElseThrow(() -> new IllegalDomainException("Reservation not found"));

		reservation.confirmOrder();

		this.totalQuantity -= reservation.getQuantity().getQuantity();
		this.productOutputs.add(new ProductOutput(orderId, productId, reservation.getQuantity().getQuantity()));
	}
	
	public Reservations getReservationId(String reservationId) {
		Reservations reservation = reservations.stream()
		.filter(r -> r.getId().equals(reservationId)).findFirst()
		.orElseThrow(() -> new IllegalDomainException("Reservation not found"));

		return reservation;
	}

	public IdStock getId() {
		return id;
	}

	public IdProduct getProductId() {
		return productId;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public List<Reservations> getReservations() {
		return reservations;
	}

	public List<ProductInput> getProductInput() {
		return productInputs;
	}

	public List<ProductOutput> getProductOutput() {
		return productOutputs;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, productInputs, productOutputs, quantityAvailable, reservations, totalQuantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return Objects.equals(productId, other.productId) && Objects.equals(productInputs, other.productInputs)
				&& Objects.equals(productOutputs, other.productOutputs) && quantityAvailable == other.quantityAvailable
				&& Objects.equals(reservations, other.reservations) && totalQuantity == other.totalQuantity;
	}
}
