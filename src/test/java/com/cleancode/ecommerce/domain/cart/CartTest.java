package com.cleancode.ecommerce.domain.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

public class CartTest {

	private CartId cartId;
	private CustomerId customerId;
	private CartItemId cartItemId;
	private ProductId productId;
	private Name productName;
	private Quantity quantity;
	private Price price;
	private ReservationId reservationId;

	@BeforeEach
	void setUp() {
		cartId = new CartId(UUID.randomUUID().toString());
		customerId = new CustomerId(UUID.randomUUID().toString());
		cartItemId = new CartItemId(UUID.randomUUID().toString());
		productId = new ProductId(UUID.randomUUID().toString());
		productName = new Name("Book Java");
		quantity = new Quantity(2);
		price = new Price(BigDecimal.valueOf(50), TypeCoin.DOLAR);
		reservationId = new ReservationId(UUID.randomUUID().toString());
	}

	@Test
	void shouldCreateCartSuccessfully() {
		Cart cart = new Cart(cartId, customerId);
		assertNotNull(cart.getCartId());
		assertNotNull(cart.getCustomerId());
		assertTrue(cart.getCartItens().isEmpty());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldThrowWhenCustomerIdIsNull() {
		assertThrows(IllegalDomainException.class, () -> new Cart(cartId, null));
	}

	@Test
	void shouldThrowWhenCartIdOrCustomerIdNullInFullConstructor() {
		assertThrows(IllegalCartException.class,
				() -> new Cart(null, customerId, List.of(), LocalDateTime.now(), LocalDateTime.now()));
		assertThrows(IllegalCartException.class,
				() -> new Cart(cartId, null, List.of(), LocalDateTime.now(), LocalDateTime.now()));
	}

	@Test
	void shouldAddProductToCart() {
		Cart cart = new Cart(cartId, customerId);

		cart.addProductToCart(cartItemId, productId, productName, quantity, price, reservationId);

		assertEquals(1, cart.getCartItens().size());
		assertEquals(BigDecimal.valueOf(100), cart.getTotalPrice().getPrice()); // 2 * 50
	}

	@Test
	void shouldIncreaseQuantityWhenProductAlreadyExists() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, new Quantity(1), price, reservationId);

		cart.addProductToCart(cartItemId, productId, productName, new Quantity(3), price, reservationId);

		assertEquals(1, cart.getCartItens().size());
		assertEquals(BigDecimal.valueOf(200), cart.getTotalPrice().getPrice()); // (1+3) * 50
	}

	@Test
	void shouldThrowWhenAddingProductWithNullFields() {
		Cart cart = new Cart(cartId, customerId);

		assertThrows(IllegalCartException.class,
				() -> cart.addProductToCart(cartItemId, null, productName, quantity, price, reservationId));
		assertThrows(IllegalCartException.class,
				() -> cart.addProductToCart(cartItemId, productId, null, quantity, price, reservationId));
	}

	@Test
	void shouldChangeProductQuantity() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, new Quantity(1), price, reservationId);

		cart.changeProductQuantity(cartItemId, new Quantity(5));

		assertEquals(BigDecimal.valueOf(250), cart.getTotalPrice().getPrice()); // 5 * 50
	}

	@Test
	void shouldThrowWhenChangingQuantityOfNonexistentProduct() {
		Cart cart = new Cart(cartId, customerId);

		assertThrows(IllegalCartException.class, () -> cart.changeProductQuantity(cartItemId, new Quantity(2)));
	}

	@Test
	void shouldRemoveProductFromCart() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, price, reservationId);

		cart.removeProductFromCart(productId);

		assertTrue(cart.getCartItens().isEmpty());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
	}

	@Test
	void shouldThrowWhenRemovingNonexistentProduct() {
		Cart cart = new Cart(cartId, customerId);

		assertThrows(IllegalCartException.class, () -> cart.removeProductFromCart(productId));
	}

	@Test
	void shouldRemoveAllProducts() {
		Cart cart = new Cart(cartId, customerId);
		cart.addProductToCart(cartItemId, productId, productName, quantity, price, reservationId);

		cart.removeAllProducts();

		assertTrue(cart.getCartItens().isEmpty());
		assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
	}
}