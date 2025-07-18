package com.cleancode.ecommerce.customer.infra.handeler;

import java.time.LocalDateTime;

public record DetailsError(LocalDateTime timestamp, int status, String error, String message) {

}
