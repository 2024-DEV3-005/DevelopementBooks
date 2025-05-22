package com.store.book.service.model;

import java.math.BigDecimal;
import java.util.Set;

import lombok.Getter;

@Getter
public class BookQuantityDetailSet {

	private final Set<Book> books;
	private final Integer discountPercentage;
	private final BigDecimal orderTotal;
	private final BigDecimal amountAfterDiscount;

	public BookQuantityDetailSet(Set<Book> books) {
		this.books = books;
		this.discountPercentage = computeDiscountPercentage();
		this.orderTotal = computeTotalAmount();
		this.amountAfterDiscount = computeAmountAfterDiscount();
	}

	private Integer computeDiscountPercentage() {
		return Discount.findDiscountByNumberOfBooks(books.size());
	}

	private BigDecimal computeTotalAmount() {
		return books.stream().map(Book::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private BigDecimal computeAmountAfterDiscount() {
		return orderTotal
				.multiply(BigDecimal.ONE.subtract(new BigDecimal(discountPercentage).divide(BigDecimal.valueOf(100))));
	}

}