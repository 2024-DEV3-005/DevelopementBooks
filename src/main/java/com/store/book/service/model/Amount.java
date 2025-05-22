package com.store.book.service.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Amount {

	BigDecimal totalPrice;

	BigDecimal priceAfterDiscount;

	Integer discountPercentage;
}
