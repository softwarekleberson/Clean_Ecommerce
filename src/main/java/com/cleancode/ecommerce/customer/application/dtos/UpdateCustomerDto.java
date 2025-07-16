package com.cleancode.ecommerce.customer.application.dtos;

import java.time.LocalDate;

import com.cleancode.ecommerce.customer.domain.customer.TypePhone;

public record UpdateCustomerDto(String name, LocalDate birth, String ddd, String phone, TypePhone typePhone) {
}
