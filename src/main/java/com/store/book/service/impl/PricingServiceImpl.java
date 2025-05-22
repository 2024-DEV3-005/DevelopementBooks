package com.store.book.service.impl;

import static com.store.book.constants.BookConstants.FIVE_PERCENTAGE_OFFER;
import static com.store.book.constants.BookConstants.PERCENTAGE_DIVISOR;
import static com.store.book.constants.BookConstants.TWO_BOOKS;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.store.book.service.PricingService;
import com.store.book.service.model.Amount;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;

@Service("pricingService")
public class PricingServiceImpl implements PricingService {

	@Override
	public Amount getPrice(Basket basket) {

		BigDecimal orderTotal = getTotalPrice(basket);

		if (basket.getDistinctBooks().size() == TWO_BOOKS) {
			BigDecimal discountedPrice = computePriceAfterDiscount(orderTotal, FIVE_PERCENTAGE_OFFER);
			return new Amount(orderTotal, discountedPrice, 5);
		} else {
			return new Amount(orderTotal, orderTotal, 0);
		}
	}

	private BigDecimal getTotalPrice(Basket basket) {
		return basket.getDistinctBooks().stream().map(Book::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

	}

	private BigDecimal computePriceAfterDiscount(BigDecimal totalPrice, int discount) {
		BigDecimal discountRate = BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(PERCENTAGE_DIVISOR));
		return totalPrice.multiply(BigDecimal.ONE.subtract(discountRate));
	}

}