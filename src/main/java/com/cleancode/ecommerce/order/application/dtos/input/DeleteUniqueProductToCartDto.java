package com.cleancode.ecommerce.order.application.dtos.input;

import jakarta.validation.constraints.NotBlank;

public record DeleteUniqueProductToCartDto(@NotBlank String cartItemId, @NotBlank String reservationId) {

}
