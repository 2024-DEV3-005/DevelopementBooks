package com.store.book.service.impl;

import static com.store.book.constants.BookTestConstants.FIRST_SET;
import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_FIVE_BOOKS;
import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_FOUR_BOOKS;
import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_ONE_BOOK;
import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_THREE_BOOKS;
import static com.store.book.constants.BookTestConstants.OFFER_PERCENTAGE_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.ONE_BOOK_IN_A_SET;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_FIVE_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_AFTER_DISCOUNT_FOR_TWO_ELIGIBLE_AND_ONE_NORMAL_BOOK;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_FIVE_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_FOUR_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_ONE_BOOK;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_THE_BOOK;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_THREE_BOOKS;
import static com.store.book.constants.BookTestConstants.PRICE_FOR_TWO_BOOKS;
import static com.store.book.constants.BookTestConstants.SECOND_SET;
import static com.store.book.constants.BookTestConstants.SET_OF_TWO_BOOKS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.BookQuanityDetail;
import com.store.book.service.model.OrderSummary;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PricingServiceImplTest {

	@Autowired
	PricingServiceImpl pricingService;

	@Test
	void shouldReturnThePriceOfTheBookWithGivenSerialNumber() {
		Basket basket = new Basket(List.of(new BookQuanityDetail(Book.CLEAN_CODE, 1)));
		assertEquals(new BigDecimal(PRICE_FOR_THE_BOOK), pricingService.getOrderSummary(basket).getTotalPrice());
	}

	@Test
	void shouldReturnTheTotalPriceforMultipleBooks() {
		BookQuanityDetail forBookOne = new BookQuanityDetail(Book.CLEAN_CODE, 1);
		BookQuanityDetail forBookTwo = new BookQuanityDetail(Book.CLEAN_CODER, 1);
		Basket basket = new Basket(List.of(forBookOne, forBookTwo));
		assertEquals(new BigDecimal(PRICE_FOR_TWO_BOOKS), pricingService.getOrderSummary(basket).getTotalPrice());
	}

	@Test
	void shouldReturnFivePercentageDiscountForTwoDifferentBooks() {
		BookQuanityDetail forBookOne = new BookQuanityDetail(Book.CLEAN_CODE, 1);
		BookQuanityDetail forBookTwo = new BookQuanityDetail(Book.CLEAN_CODER, 1);
		Basket basket = new Basket(List.of(forBookOne, forBookTwo));
		OrderSummary billedAmount = pricingService.getOrderSummary(basket);

		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_TWO_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS,
					billedAmount.getBookToOrderList().get(FIRST_SET).getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS), billedAmount.getFinalPriceAfterDiscount());
		});
	}

	@Test
	void shouldReturnTenPercentageDiscountForThreeDifferentBooks() {
		BookQuanityDetail forBookOne = new BookQuanityDetail(Book.CLEAN_CODE, 1);
		BookQuanityDetail forBookTwo = new BookQuanityDetail(Book.CLEAN_CODER, 1);
		BookQuanityDetail forBookThree = new BookQuanityDetail(Book.CLEAN_ARCHITECTURE, 1);

		Basket basket = new Basket(List.of(forBookOne, forBookTwo, forBookThree));
		OrderSummary billedAmount = pricingService.getOrderSummary(basket);

		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_THREE_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_THREE_BOOKS,
					billedAmount.getBookToOrderList().get(FIRST_SET).getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_THREE_BOOKS),
					billedAmount.getFinalPriceAfterDiscount());
		});
	}

	@Test
	void shouldReturnFifteenPercentageDiscountForFourDifferentBooks() {
		BookQuanityDetail forBookOne = new BookQuanityDetail(Book.CLEAN_CODE, 1);
		BookQuanityDetail forBookTwo = new BookQuanityDetail(Book.CLEAN_CODER, 1);
		BookQuanityDetail forBookThree = new BookQuanityDetail(Book.CLEAN_ARCHITECTURE, 1);
		BookQuanityDetail forBookFour = new BookQuanityDetail(Book.TEST_DRIVEN_DEVELOPMENT, 1);

		Basket basket = new Basket(List.of(forBookOne, forBookTwo, forBookThree, forBookFour));
		OrderSummary billedAmount = pricingService.getOrderSummary(basket);

		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_FOUR_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_FOUR_BOOKS,
					billedAmount.getBookToOrderList().get(FIRST_SET).getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_FOUR_BOOKS),
					billedAmount.getFinalPriceAfterDiscount());
		});
	}

	@Test
	void shouldReturnTwentyPercentageDiscountForFiveDifferentBooks() {
		BookQuanityDetail forBookOne = new BookQuanityDetail(Book.CLEAN_CODE, 1);
		BookQuanityDetail forBookTwo = new BookQuanityDetail(Book.CLEAN_CODER, 1);
		BookQuanityDetail forBookThree = new BookQuanityDetail(Book.CLEAN_ARCHITECTURE, 1);
		BookQuanityDetail forBookFour = new BookQuanityDetail(Book.TEST_DRIVEN_DEVELOPMENT, 1);
		BookQuanityDetail forBookFive = new BookQuanityDetail(Book.LEGACY_CODE, 1);

		Basket basket = new Basket(List.of(forBookOne, forBookTwo, forBookThree, forBookFour, forBookFive));
		OrderSummary billedAmount = pricingService.getOrderSummary(basket);

		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_FIVE_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_FIVE_BOOKS,
					billedAmount.getBookToOrderList().get(FIRST_SET).getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_FIVE_BOOKS),
					billedAmount.getFinalPriceAfterDiscount());
		});
	}

	@Test
	void calculateFivePercentageDiscountForTwoDifferentBooksAndNoDiscountForOneSameBook() {
		BookQuanityDetail forBookOne = new BookQuanityDetail(Book.CLEAN_CODE, 2);
		BookQuanityDetail forBookTwo = new BookQuanityDetail(Book.CLEAN_CODER, 1);

		Basket basket = new Basket(List.of(forBookOne, forBookTwo));
		OrderSummary billedAmount = pricingService.getOrderSummary(basket);
		assertAll(() -> {
			assertEquals(new BigDecimal(PRICE_FOR_THREE_BOOKS), billedAmount.getTotalPrice());
			assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS,
					billedAmount.getBookToOrderList().get(FIRST_SET).getDiscountPercentage());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_TWO_ELIGIBLE_AND_ONE_NORMAL_BOOK),
					billedAmount.getFinalPriceAfterDiscount());
			assertEquals(new BigDecimal(PRICE_FOR_TWO_BOOKS),
					billedAmount.getBookToOrderList().get(FIRST_SET).getOrderTotal());
			assertEquals(new BigDecimal(PRICE_AFTER_DISCOUNT_FOR_TWO_BOOKS),
					billedAmount.getBookToOrderList().get(FIRST_SET).getAmountAfterDiscount());
			assertEquals(OFFER_PERCENTAGE_FOR_TWO_BOOKS,
					billedAmount.getBookToOrderList().get(FIRST_SET).getDiscountPercentage());
			assertEquals(SET_OF_TWO_BOOKS, billedAmount.getBookToOrderList().get(FIRST_SET).getBooks().size());
			assertEquals(new BigDecimal(PRICE_FOR_ONE_BOOK),
					billedAmount.getBookToOrderList().get(SECOND_SET).getOrderTotal());
			assertEquals(new BigDecimal(PRICE_FOR_ONE_BOOK),
					billedAmount.getBookToOrderList().get(SECOND_SET).getAmountAfterDiscount());
			assertEquals(OFFER_PERCENTAGE_FOR_ONE_BOOK,
					billedAmount.getBookToOrderList().get(SECOND_SET).getDiscountPercentage());
			assertEquals(ONE_BOOK_IN_A_SET, billedAmount.getBookToOrderList().get(SECOND_SET).getBooks().size());
		});
	}

}
