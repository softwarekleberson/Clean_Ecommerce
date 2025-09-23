package com.cleancode.ecommerce.stock.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.stock.domain.exception.IllegalReservationException;

public class Stock {

	public static final int MIN_QUANTITY = 0;

	private StockId stockId;
	private ProductId productId;
	private int totalQuantity;
	private int quantityAvailable; // sempre recalculado
	private List<Reservations> reservations = new ArrayList<>();
	private List<ProductInput> productInputs = new ArrayList<>();
	private List<ProductOutput> productOutputs = new ArrayList<>();

	public Stock(ProductId productId) {
		this.stockId = new StockId();
		this.productId = productId;
	}

	public Stock(StockId id, ProductId productId, int totalQuantity) {
		this.stockId = id;
		this.productId = productId;
		this.totalQuantity = totalQuantity;
		this.quantityAvailable = totalQuantity;
	}

	public void addReservations(List<Reservations> reservations) {
		this.reservations.addAll(reservations);
		recalculateQuantityAvailable();
	}

	public void addProductInput(List<ProductInput> productInput) {
		this.productInputs.addAll(productInput);
	}

	public void addProductOutput(List<ProductOutput> productOutput) {
		this.productOutputs.addAll(productOutput);
	}

	public void addProductInput(int quantity, ProductQuality productQuality, Price purchasePrice, String supplier) {
		if (quantity <= MIN_QUANTITY) {
			throw new IllegalDomainException("Quantity must be positive");
		}

		this.totalQuantity += quantity;
		ProductInput entryMovement = new ProductInput(quantity, productQuality, purchasePrice, supplier);
		this.productInputs.add(entryMovement);

		recalculateQuantityAvailable();
	}

	public Reservations reservation(String cartId, String customerId, int quantity) {
		if (quantity > this.quantityAvailable) {
			throw new IllegalReservationException("Insufficient stock");
		}

		Reservations reservation = new Reservations(cartId, customerId, quantity);
		this.reservations.add(reservation);

		recalculateQuantityAvailable();
		return reservation;
	}

	public void cancelReservation(String reservationId) {
		Reservations reservation = reservations.stream().filter(r -> r.getReservationId().equals(reservationId))
				.findFirst().orElseThrow(() -> new IllegalReservationException("Reservation not found"));

		if (reservation.getReserveStatus() == ReserveStatus.CANCELED) {
			throw new IllegalReservationException("This reservation was previously cancelled");
		}

		reservation.cancel();
		recalculateQuantityAvailable();
	}

	public void confirmOrder(String orderId, String productId, String reservationId) {
		Reservations reservation = reservations.stream().filter(r -> r.getReservationId().equals(reservationId))
				.findFirst().orElseThrow(() -> new IllegalReservationException("Reservation not found"));

		reservation.confirmOrder();

		this.totalQuantity -= reservation.getQuantity().getQuantity();
		this.productOutputs.add(new ProductOutput(new OrderId(orderId), new ProductId(productId),
				reservation.getQuantity().getQuantity()));

		recalculateQuantityAvailable();
	}

	private void recalculateQuantityAvailable() {
		int totalReserved = this.reservations.stream().filter(r -> r.getReserveStatus() == ReserveStatus.ACTIVE)
				.mapToInt(r -> r.getQuantity().getQuantity()).sum();

		this.quantityAvailable = this.totalQuantity - totalReserved;
	}

	public Reservations getReservationId(String reservationId) {
		return reservations.stream().filter(r -> r.getReservationId().equals(reservationId)).findFirst()
				.orElseThrow(() -> new IllegalReservationException("Reservation not found"));
	}

	public StockId getStockId() {
		return stockId;
	}

	public ProductId getProductId() {
		return productId;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public int getQuantityAvailable() {
		return quantityAvailable;
	}

	public List<Reservations> getReservations() {
		return Collections.unmodifiableList(this.reservations);
	}

	public List<ProductInput> getProductInput() {
		return Collections.unmodifiableList(this.productInputs);
	}

	public List<ProductOutput> getProductOutput() {
		return Collections.unmodifiableList(this.productOutputs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId, productInputs, productOutputs, quantityAvailable, reservations, totalQuantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		return quantityAvailable == other.quantityAvailable && totalQuantity == other.totalQuantity
				&& Objects.equals(productId, other.productId) && Objects.equals(productInputs, other.productInputs)
				&& Objects.equals(productOutputs, other.productOutputs)
				&& Objects.equals(reservations, other.reservations);
	}

	@Override
	public String toString() {
		return "Stock [id=" + stockId + ", productId=" + productId + ", totalQuantity=" + totalQuantity
				+ ", quantityAvailable=" + quantityAvailable + ", reservations=" + reservations + ", productInputs="
				+ productInputs + ", productOutputs=" + productOutputs + "]";
	}
}
