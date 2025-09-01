package com.cleancode.ecommerce.domain.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.CartId;
import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;

public class CartTest {

	private CartItemId cartItemId;
	private CartId cartId;
	private CustomerId customerId;
	private ProductId productId;
	private Name productName;
	private Quantity quantity;
	private Price price;

	@BeforeEach
	void setUp() {
		cartItemId = new CartItemId("cartItemId-123");
		cartId = new CartId("cart-123");
		customerId = new CustomerId("cust-123");
		productId = new ProductId("prod-123");
		productName = new Name("Notebook");
		quantity = new Quantity(2);
		price = new Price(new BigDecimal("100.00"), TypeCoin.DOLAR);
	}

	@Test
	void shouldCreateCartSuccessfully() {
		Cart cart = new Cart(cartId, customerId);
		assertNotNull(cart.getCartId());
		assertNotNull(cart.getCustomerId());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldThrowExceptionWhenCustomerIsNull() {
		assertThrows(IllegalDomainException.class, () -> new Cart(cartId, null));
	}

	@Test
	void shouldAddProductToCart() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, price);

		assertEquals(1, cart.getCartItens().size());
		assertEquals(new BigDecimal("200.00"), cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldIncreaseQuantityWhenProductAlreadyExists() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, new Quantity(1), price);
		cart.addProductToCart(cartItemId, productId, productName, new Quantity(2), price);

		assertEquals(1, cart.getCartItens().size());
		assertEquals(new BigDecimal("300.00"), cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldChangeProductQuantity() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, new Quantity(1), price);
		cart.changeProductQuantity(cartItemId, new Quantity(5));

		assertEquals(new BigDecimal("500.00"), cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldThrowExceptionWhenChangingQuantityForNonexistentProduct() {
		Cart cart = new Cart(cartId, customerId);
		assertThrows(IllegalDomainException.class,
				() -> cart.changeProductQuantity(new CartItemId("invalid"), new Quantity(3)));
	}

	@Test
	void shouldRemoveProductFromCart() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, price);
		cart.removeProductFromCart(productId);

		assertEquals(0, cart.getCartItens().size());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldThrowExceptionWhenRemovingNonexistentProduct() {
		Cart cart = new Cart(cartId, customerId);
		assertThrows(IllegalDomainException.class, () -> cart.removeProductFromCart(productId));
	}

	@Test
	void shouldRemoveAllProducts() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, price);
		cart.removeAllProducts();

		assertEquals(0, cart.getCartItens().size());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldKeepCreatedAtAndUpdateUpdatedAt() throws InterruptedException {
		Cart cart = new Cart(cartId, customerId);
		LocalDateTime createdAt = cart.getCreatedAt();
		LocalDateTime updatedAtBefore = cart.getUpdatedAt();

		Thread.sleep(10); 
		cart.addProductToCart(cartItemId, productId, productName, quantity, price);

		assertEquals(createdAt, cart.getCreatedAt());
		assertTrue(cart.getUpdatedAt().isAfter(updatedAtBefore));
	}
}