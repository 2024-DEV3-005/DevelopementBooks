package com.store.book.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PricingServiceImplTest {

	private static final int PRICE_FOR_THE_BOOK = 50;
	private static final String SERIAL_NO_FOR_FIRST_BOOK = "1";
	
	@Autowired
	PricingServiceImpl pricingService;

	@Test
	void shouldReturnThePriceOfTheBookWithGivenSerialNumber() {
		assertEquals(new BigDecimal(PRICE_FOR_THE_BOOK), pricingService.getPrice(SERIAL_NO_FOR_FIRST_BOOK));
	}
}
