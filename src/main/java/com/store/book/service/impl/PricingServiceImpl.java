package com.store.book.service.impl;

import static com.store.book.constants.BookConstants.PERCENTAGE_DIVISOR;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.store.book.service.PricingService;
import com.store.book.service.model.Amount;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.Discount;

@Service("pricingService")
public class PricingServiceImpl implements PricingService {

	@Override
	public Amount getPrice(Basket basket) {

		BigDecimal orderTotal = getTotalPrice(basket);

		Integer discount = Discount.findDiscountByNumberOfBooks(basket.getDistinctBooks().size());
		BigDecimal discountedPrice = computePriceAfterDiscount(orderTotal, discount);
		return new Amount(orderTotal, discountedPrice, discount);
	}

	private BigDecimal getTotalPrice(Basket basket) {
		return basket.getDistinctBooks().stream().map(Book::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

	}

	private BigDecimal computePriceAfterDiscount(BigDecimal totalPrice, int discount) {
		BigDecimal discountRate = BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(PERCENTAGE_DIVISOR));
		return totalPrice.multiply(BigDecimal.ONE.subtract(discountRate));
	}

}