package com.cleancode.ecommerce.event;

import java.time.Instant;

public interface DomainEvent {

    Instant occurredOn();
}
