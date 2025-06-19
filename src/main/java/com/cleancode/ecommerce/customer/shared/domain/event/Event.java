package com.cleancode.ecommerce.customer.shared.domain.event;

import java.time.LocalDateTime;
import java.util.Map;

public interface Event {

	LocalDateTime moment();
	TypeEvent type();
	Map<String, Object> informations();
}
