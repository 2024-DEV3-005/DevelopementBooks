package com.store.book.service;

import java.math.BigDecimal;

public interface PricingService {
	
	BigDecimal getPrice (String serialNumber);

}
