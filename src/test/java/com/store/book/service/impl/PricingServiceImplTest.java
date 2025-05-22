package com.store.book.service.impl;

import static com.store.book.constants.BookTestConstants.PRICE_FOR_THE_BOOK;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.SERIAL_NO_FOR_FIRST_BOOK;
import static com.store.book.constants.BookTestConstants.SERIAL_NO_FOR_SECOND_BOOK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.book.request.model.ShoppingBasket;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PricingServiceImplTest {

	@Autowired
	PricingServiceImpl pricingService;

	@Test
	void shouldReturnThePriceOfTheBookWithGivenSerialNumber() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.setSerialNumberOfBook(List.of(SERIAL_NO_FOR_FIRST_BOOK));
		assertEquals(new BigDecimal(PRICE_FOR_THE_BOOK), pricingService.getPrice(basket));
	}

	@Test
	void shouldReturnTheTotalPriceforMultipleBooks() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.setSerialNumberOfBook(List.of(SERIAL_NO_FOR_FIRST_BOOK, SERIAL_NO_FOR_SECOND_BOOK));
		assertEquals(new BigDecimal(PRICE_FOR_TWO_BOOKS), pricingService.getPrice(basket));
	}
}
