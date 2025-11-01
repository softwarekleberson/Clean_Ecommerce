package com.cleancode.ecommerce.domain.cart;

import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.order.domain.cart.Cart;
import com.cleancode.ecommerce.order.domain.cart.CartId;
import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.exception.IllegalCartException;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;
import com.cleancode.ecommerce.stock.domain.ReservationId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

	private CartId cartId;
	private CustomerId customerId;
	private ProductId productId;
	private CartItemId cartItemId;
	private Name productName;
	private Price unitPrice;
	private Quantity quantity;
	private ReservationId reservationId;

	@BeforeEach
	void setUp() {
		cartId = new CartId("cart");
		customerId = new CustomerId("customer");
		productId = new ProductId();
		cartItemId = new CartItemId();
		productName = new Name("Notebook Gamer");
		unitPrice = new Price(BigDecimal.valueOf(2000), TypeCoin.DOLAR);
		quantity = new Quantity(2);
		reservationId = new ReservationId();
	}

	@Test
	void shouldCreateCartSuccessfully() {
		Cart cart = new Cart(cartId, customerId);
		assertNotNull(cart.getCartId());
		assertEquals(customerId, cart.getCustomerId());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
		assertTrue(cart.getCartItens().isEmpty());
	}

	@Test
	void shouldThrowExceptionWhenCustomerIdIsNull() {
		assertThrows(IllegalDomainException.class, () -> new Cart(cartId, null));
	}

	@Test
	void shouldAddProductToCartSuccessfully() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		assertEquals(1, cart.getCartItens().size());
		assertEquals(BigDecimal.valueOf(4000), cart.getTotalPrice().getPrice()); // 2000 * 2
	}

	@Test
	void shouldNotAllowAddingSameProductTwice() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		assertThrows(IllegalCartException.class,
				() -> cart.addProductToCart(cartItemId, productId, productName, quantity, unitPrice, reservationId));
	}

	@Test
	void shouldChangeProductQuantitySuccessfully() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		cart.changeProductQuantity(cartItemId, new Quantity(3));

		assertEquals(BigDecimal.valueOf(6000), cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldThrowWhenChangeQuantityForNonExistentProduct() {
		Cart cart = new Cart(cartId, customerId);

		assertThrows(IllegalCartException.class, () -> cart.changeProductQuantity(cartItemId, new Quantity(5)));
	}

	@Test
	void shouldRemoveProductSuccessfully() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		cart.removeProductFromCart(cartItemId);

		assertTrue(cart.getCartItens().isEmpty());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldThrowWhenRemovingNonExistentProduct() {
		Cart cart = new Cart(cartId, customerId);

		assertThrows(IllegalCartException.class, () -> cart.removeProductFromCart(cartItemId));
	}

	@Test
	void shouldRemoveAllProducts() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, unitPrice, reservationId);

		cart.removeAllProducts();

		assertTrue(cart.getCartItens().isEmpty());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldUseDefaultDatesWhenNotProvided() {
		Cart cart = new Cart(cartId, customerId, null, null, null);

		assertNotNull(cart.getCreatedAt());
		assertNotNull(cart.getUpdatedAt());
	}

	@Test
	void shouldThrowWhenCartIdIsNullInSecondConstructor() {
		assertThrows(IllegalCartException.class,
				() -> new Cart(null, customerId, null, LocalDateTime.now(), LocalDateTime.now()));
	}
}
