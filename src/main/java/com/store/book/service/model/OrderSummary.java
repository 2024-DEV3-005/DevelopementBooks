package com.store.book.service.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;

@Getter
public class OrderSummary {

	List<BookQuantityDetailSet> bookToOrderList;
	BigDecimal totalPrice;
	BigDecimal finalPriceAfterDiscount;

	public OrderSummary(List<BookQuantityDetailSet> bookToOrderList) {
		this.bookToOrderList = bookToOrderList;
		this.totalPrice = computeTotalAmount();
		this.finalPriceAfterDiscount = computeFinalAmountAfterDiscount();
	}

	private BigDecimal computeTotalAmount() {
		return bookToOrderList.stream().map(BookQuantityDetailSet::getOrderTotal).reduce(BigDecimal.ZERO,
				BigDecimal::add);
	}

	private BigDecimal computeFinalAmountAfterDiscount() {
		return bookToOrderList.stream().map(BookQuantityDetailSet::getAmountAfterDiscount).reduce(BigDecimal.ZERO,
				BigDecimal::add);
	}

}
