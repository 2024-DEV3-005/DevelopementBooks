package com.store.book.response.model;

import java.math.BigDecimal;
import java.util.List;

import com.store.book.request.model.BookInfo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookQuantitySet {

	private List<BookInfo> books;
	@Schema(example = "15")
	private Integer discountPercentage;
	@Schema(example = "200.0")
	private BigDecimal orderTotal;
	@Schema(example = "150.0")
	private BigDecimal priceAfterDiscount;

}