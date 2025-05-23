package com.store.book.response.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderSummaryResponse {

	List<BookQuantitySet> bookToOrderList;
	@Schema(example = "200.0", description = "Total price of the Books Selected")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	BigDecimal totalPrice;
	@Schema(example = "150.0", description = "Discounted price")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	BigDecimal finalPriceAfterDiscount;
}
