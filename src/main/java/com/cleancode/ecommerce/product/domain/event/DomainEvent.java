package com.cleancode.ecommerce.product.domain.event;

import java.time.Instant;

public interface DomainEvent {

    Instant occurredOn();
}
