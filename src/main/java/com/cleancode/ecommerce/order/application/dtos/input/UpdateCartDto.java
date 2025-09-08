package com.cleancode.ecommerce.order.application.dtos.input;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UpdateCartDto(@NotBlank String cartItemId, @Min(0) int quantity) {

}
