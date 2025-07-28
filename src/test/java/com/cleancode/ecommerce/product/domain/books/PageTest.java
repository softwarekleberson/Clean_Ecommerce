package com.cleancode.ecommerce.product.domain.books;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.cleancode.ecommerce.customer.domain.customer.exception.IllegalDomainException;

class PageTest {

    @Test
    @DisplayName("Deve criar uma página válida com número positivo")
    void testCreateValidPage() {
        Page page = new Page(10);
        assertEquals(10, page.getPage());
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar página com número menor que 1")
    void testInvalidPageThrowsException() {
        IllegalDomainException exception = assertThrows(IllegalDomainException.class, () -> {
            new Page(0);
        });
        assertEquals("Number page not be less than 0", exception.getMessage());
    }

    @Test
    @DisplayName("Deve considerar páginas iguais se o número for o mesmo")
    void testEqualsAndHashCode() {
        Page p1 = new Page(100);
        Page p2 = new Page(100);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("Deve considerar páginas diferentes se os números forem diferentes")
    void testNotEquals() {
        Page p1 = new Page(10);
        Page p2 = new Page(20);

        assertNotEquals(p1, p2);
    }

    @Test
    @DisplayName("Deve retornar false ao comparar com objeto de outra classe")
    void testEqualsWithDifferentClass() {
        Page p = new Page(5);
        Object obj = new Object();

        assertNotEquals(p, obj);
    }

    @Test
    @DisplayName("Deve retornar true ao comparar uma página com ela mesma")
    void testEqualsWithSameInstance() {
        Page p = new Page(7);
        assertEquals(p, p);
    }

    @Test
    @DisplayName("Deve retornar false ao comparar página com null")
    void testEqualsWithNull() {
        Page p = new Page(3);
        assertNotEquals(null, p);
    }
}
