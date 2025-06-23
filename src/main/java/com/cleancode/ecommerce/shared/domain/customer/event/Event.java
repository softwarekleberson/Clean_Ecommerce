package com.cleancode.ecommerce.shared.domain.customer.event;

import java.time.LocalDateTime;
import java.util.Map;

public interface Event {

	LocalDateTime moment();
	TypeEvent type();
	Map<String, Object> informations();
}
