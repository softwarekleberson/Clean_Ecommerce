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

public class CartTest {

	private Cart cart;
    private CustomerId customerId;
    private ProductId productId;
    private Name productName;
    private Quantity quantity;
    private Price price;

    @BeforeEach
    void setUp() {
        customerId = new CustomerId("cust-123");
        productId = new ProductId("prod-1");
        productName = new Name("Produto Teste");
        quantity = new Quantity(2);
        price = new Price(BigDecimal.TEN, TypeCoin.DOLAR);
        cart = new Cart(customerId);
    }

    @Test
    void shouldThrowExceptionWhenCustomerIdIsNull() {
        assertThrows(IllegalDomainException.class, () -> new Cart(null));
    }

    @Test
    void shouldAddProductToCartAndCalculateTotalPrice() {
        cart.addProductToCart(productId, productName, quantity, price);

        assertEquals(1, cart.getCartItens().size());
        assertEquals(new BigDecimal("20"), cart.getTotalPrice().getPrice());
        assertEquals(TypeCoin.DOLAR, cart.getTotalPrice().getCoin());
    }

    @Test
    void shouldUpdateQuantityProduct() {
        cart.addProductToCart(productId, productName, quantity, price);

        cart.updateQuantityProduct(
                productId.getProductId(),
                productId,
                productName,
                new Quantity(5),
                price
        );

        assertEquals(1, cart.getCartItens().size());
        assertEquals(new BigDecimal("50"), cart.getTotalPrice().getPrice());
    }

    @Test
    void shouldThrowExceptionWhenUpdateQuantityProductWithNullId() {
        assertThrows(IllegalDomainException.class, () ->
                cart.updateQuantityProduct(null, productId, productName, quantity, price));
    }

    @Test
    void shouldRemoveAllProductsFromCart() {
        cart.addProductToCart(productId, productName, quantity, price);

        cart.removeAllProductToCart();

        assertEquals(0, cart.getCartItens().size());
        assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
    }

    @Test
    void shouldRemoveProductFromCart() {
        cart.addProductToCart(productId, productName, quantity, price);

        cart.removeProductToCart(productId.getProductId());

        assertTrue(cart.getCartItens().isEmpty());
        assertEquals(BigDecimal.ZERO, cart.getTotalPrice().getPrice());
    }

    @Test
    void shouldThrowExceptionWhenRemoveProductWithNullId() {
        assertThrows(IllegalDomainException.class, () ->
                cart.removeProductToCart(null));
    }

    @Test
    void shouldThrowExceptionWhenRemoveProductNotInCart() {
        cart.addProductToCart(productId, productName, quantity, price);

        assertThrows(IllegalDomainException.class, () ->
                cart.removeProductToCart("other-product"));
    }
}