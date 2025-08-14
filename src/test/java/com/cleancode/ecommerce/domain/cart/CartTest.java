package com.cleancode.ecommerce.domain.cart;

import com.cleancode.ecommerce.cart.domain.Cart;
import com.cleancode.ecommerce.customer.domain.customer.CustomerId;
import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private CustomerId customerId;
    private Cart cart;

    @BeforeEach
    void setUp() {
        customerId = new CustomerId("cust-123");
        cart = new Cart(customerId);
    }

    @Test
    void shouldCreateCartSuccessfully() {
        assertNotNull(cart.getCartId());
        assertEquals(customerId, cart.getCustomerId());
        assertTrue(cart.getCartItens().isEmpty());
        assertNotNull(cart.getCreatedAt());
        assertNotNull(cart.getUpdatedAt());
    }

    @Test
    void shouldThrowExceptionWhenCustomerIdIsNull() {
        assertThrows(IllegalDomainException.class, () -> new Cart(null));
    }

    @Test
    void shouldAddProductToCart() {
        cart.addProductToCart("p1", "Product 1", 2, BigDecimal.valueOf(10), TypeCoin.DOLAR);

        assertEquals(1, cart.getCartItens().size());
        assertEquals(BigDecimal.valueOf(20), cart.getTotalPrice().getPrice());
        assertEquals(TypeCoin.DOLAR, cart.getTotalPrice().getCoin());
    }

    @Test
    void shouldUpdateQuantityProduct() {
        cart.addProductToCart("p1", "Product 1", 1, BigDecimal.valueOf(5), TypeCoin.DOLAR);
        cart.updateQuantityProduct("p1",
                new ProductId("p1"),
                new Name("Product 1"),
                new Quantity(3),
                new Price(BigDecimal.valueOf(5), TypeCoin.DOLAR));

        assertEquals(1, cart.getCartItens().size());
        assertEquals(BigDecimal.valueOf(15), cart.getTotalPrice().getPrice());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingWithInvalidData() {
        assertThrows(IllegalDomainException.class, () ->
                cart.updateQuantityProduct(null,
                        new ProductId("p1"),
                        new Name("Product 1"),
                        new Quantity(2),
                        new Price(BigDecimal.valueOf(5), TypeCoin.DOLAR)));
    }

    @Test
    void shouldRemoveAllProductsFromCart() {
        cart.addProductToCart("p1", "Product 1", 2, BigDecimal.valueOf(10), TypeCoin.DOLAR);
        cart.addProductToCart("p2", "Product 2", 1, BigDecimal.valueOf(15), TypeCoin.DOLAR);

        cart.removeAllProductToCart();

        assertTrue(cart.getCartItens().isEmpty());
        assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
    }

    @Test
    void shouldRemoveSpecificProductFromCart() {
        cart.addProductToCart("p1", "Product 1", 2, BigDecimal.valueOf(10), TypeCoin.DOLAR);
        cart.addProductToCart("p2", "Product 2", 1, BigDecimal.valueOf(15), TypeCoin.DOLAR);

        cart.removeProductToCart("p1");

        assertEquals(1, cart.getCartItens().size());
        assertEquals("p2", cart.getCartItens().get(0).getProductId().getProductId());
    }

    @Test
    void shouldThrowExceptionWhenRemovingProductWithInvalidId() {
        assertThrows(IllegalDomainException.class, () -> cart.removeProductToCart(null));
        assertThrows(IllegalDomainException.class, () -> cart.removeProductToCart(""));
    }

    @Test
    void shouldThrowExceptionWhenRemovingProductNotFound() {
        cart.addProductToCart("p1", "Product 1", 1, BigDecimal.valueOf(10), TypeCoin.DOLAR);
        assertThrows(IllegalDomainException.class, () -> cart.removeProductToCart("p2"));
    }
}