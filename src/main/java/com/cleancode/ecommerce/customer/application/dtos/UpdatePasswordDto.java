package com.cleancode.ecommerce.customer.application.dtos;

import jakarta.validation.constraints.NotBlank;

public record UpdatePasswordDto(@NotBlank String password, @NotBlank String confirmPassword) {

}
