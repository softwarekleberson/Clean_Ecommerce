package com.cleancode.ecommerce.domain.cart;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.order.domain.cart.CartItemId;
import com.cleancode.ecommerce.order.domain.cart.CartItens;
import com.cleancode.ecommerce.order.domain.cart.UrlProduct;
import com.cleancode.ecommerce.product.domain.ProductId;
import com.cleancode.ecommerce.shared.kernel.Name;
import com.cleancode.ecommerce.shared.kernel.Price;
import com.cleancode.ecommerce.shared.kernel.TypeCoin;
import com.cleancode.ecommerce.stock.domain.Quantity;
import com.cleancode.ecommerce.stock.domain.ReservationId;
import com.cleancode.ecommerce.stock.domain.exception.IllegalStockException;

class CartItensTest {

    private CartItemId cartItemId;
    private ProductId productId;
    private Name productName;
    private UrlProduct urlProduct;
    private Quantity quantity;
    private Price price;
    private ReservationId reservationId;

    @BeforeEach
    void setup() {
        cartItemId = new CartItemId("123");
        productId = new ProductId("P001");
        productName = new Name("Produto Teste");
        urlProduct = new UrlProduct("https://imagem.com/produto.png");
        quantity = new Quantity(2);
        price = new Price(new BigDecimal("50.00"), TypeCoin.DOLAR);
        reservationId = new ReservationId("R001");
    }

    @Test
    void deveCriarCartItemComSucesso() {
        CartItens item = new CartItens(cartItemId, productId, productName, urlProduct, quantity, price, reservationId);

        assertNotNull(item);
        assertEquals("Produto Teste", item.getProductName().getName());
        assertEquals(2, item.getQuantity().getQuantity());
        assertEquals(new BigDecimal("50.00"), item.getUnitPrice().getPrice());
    }

    @Test
    void deveLancarExcecaoQuandoAlgumParametroForNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CartItens(null, productId, productName, urlProduct, quantity, price, reservationId);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new CartItens(cartItemId, null, productName, urlProduct, quantity, price, reservationId);
        });
    }

    @Test
    void deveCalcularSubtotalCorretamente() {
        CartItens item = new CartItens(cartItemId, productId, productName, urlProduct, quantity, price, reservationId);
        Price subtotal = item.calculateSubtotal();

        assertEquals(new BigDecimal("100.00"), subtotal.getPrice());
        assertEquals(TypeCoin.DOLAR, subtotal.getCoin());
    }

    @Test
    void deveAumentarQuantidadeComSucesso() {
        CartItens item = new CartItens(cartItemId, productId, productName, urlProduct, quantity, price, reservationId);
        item.increaseQuantity(new Quantity(3));

        assertEquals(5, item.getQuantity().getQuantity());
    }

    @Test
    void deveLancarExcecaoAoAdicionarQuantidadeNegativaOuZero() {
        CartItens item = new CartItens(cartItemId, productId, productName, urlProduct, quantity, price, reservationId);

        assertThrows(IllegalStockException.class, () -> {
            item.increaseQuantity(new Quantity(0));
        });

        assertThrows(IllegalStockException.class, () -> {
            item.increaseQuantity(new Quantity(-2));
        });
    }

    @Test
    void deveAlterarQuantidadeComSucesso() {
        CartItens item = new CartItens(cartItemId, productId, productName, urlProduct, quantity, price, reservationId);
        item.changeQuantity(new Quantity(10));

        assertEquals(10, item.getQuantity().getQuantity());
    }

    @Test
    void deveLancarExcecaoAoAlterarQuantidadeInvalida() {
        CartItens item = new CartItens(cartItemId, productId, productName, urlProduct, quantity, price, reservationId);

        assertThrows(IllegalStockException.class, () -> {
            item.changeQuantity(new Quantity(0));
        });

        assertThrows(IllegalStockException.class, () -> {
            item.changeQuantity(new Quantity(-5));
        });
    }
}
