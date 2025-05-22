package com.store.book.service.impl;

import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_FOUR_BOOKS;
import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_THREE_BOOKS;
import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_FOUR_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_THE_BOOK;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_THREE_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_TWO_BOOKS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.book.service.model.Amount;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PricingServiceImplTest {

	@Autowired
	PricingServiceImpl pricingService;

	@Test
	void shouldReturnThePriceOfTheBookWithGivenSerialNumber() {
		Basket basket = new Basket(List.of(Book.CLEAN_CODE));
		assertEquals(new BigDecimal(PRICE_FOR_THE_BOOK), pricingService.getPrice(basket).getTotalPrice());
	}

	@Test
	void shouldReturnTheTotalPriceforMultipleBooks() {
		Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_CODER));
		assertEquals(new BigDecimal(PRICE_FOR_TWO_BOOKS), pricingService.getPrice(basket).getTotalPrice());
	}

	@Test
	void shouldReturnFivePercentageDiscountForTwoDifferentBooks() {
		Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_CODER));
		Amount billedAmount = pricingService.getPrice(basket);

		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_TWO_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS, billedAmount.getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS), billedAmount.getPriceAfterDiscount());
		});
	}

	@Test
	void shouldReturnTenPercentageDiscountForThreeDifferentBooks() {
		Basket basket = new Basket(List.of(Book.CLEAN_CODE, Book.CLEAN_CODER, Book.CLEAN_ARCHITECTURE));
		Amount billedAmount = pricingService.getPrice(basket);

		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_THREE_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_THREE_BOOKS, billedAmount.getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS), billedAmount.getPriceAfterDiscount());
		});
	}

	@Test
	void shouldReturnFifteenPercentageDiscountForFourDifferentBooks() {
		Basket basket = new Basket(
				List.of(Book.CLEAN_CODE, Book.CLEAN_CODER, Book.CLEAN_ARCHITECTURE, Book.TEST_DRIVEN_DEVELOPMENT));
		Amount billedAmount = pricingService.getPrice(basket);

		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_FOUR_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_FOUR_BOOKS, billedAmount.getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS), billedAmount.getPriceAfterDiscount());
		});
	}

}
