package com.store.book.service.impl;

import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_THE_BOOK;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.SERIAL_NO_FOR_FIRST_BOOK;
import static com.store.book.constants.BookTestConstants.SERIAL_NO_FOR_SECOND_BOOK;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.book.request.model.ShoppingBasket;
import com.store.book.service.model.Amount;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PricingServiceImplTest {

	@Autowired
	PricingServiceImpl pricingService;

	@Test
	void shouldReturnThePriceOfTheBookWithGivenSerialNumber() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.setSerialNumberOfBook(List.of(SERIAL_NO_FOR_FIRST_BOOK));
		assertEquals(new BigDecimal(PRICE_FOR_THE_BOOK), pricingService.getPrice(basket).getTotalPrice());
	}

	@Test
	void shouldReturnTheTotalPriceforMultipleBooks() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.setSerialNumberOfBook(List.of(SERIAL_NO_FOR_FIRST_BOOK, SERIAL_NO_FOR_SECOND_BOOK));
		assertEquals(new BigDecimal(PRICE_FOR_TWO_BOOKS), pricingService.getPrice(basket).getTotalPrice());
	}

	@Test
	void shouldReturnFivePercentageDiscountForTwoDifferentBooks() {
		ShoppingBasket basket = new ShoppingBasket();
		basket.setSerialNumberOfBook(List.of(SERIAL_NO_FOR_FIRST_BOOK, SERIAL_NO_FOR_SECOND_BOOK));

		Amount billedAmount = pricingService.getPrice(basket);

		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_TWO_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS, billedAmount.getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS), billedAmount.getPriceAfterDiscount());
		});

	}

}