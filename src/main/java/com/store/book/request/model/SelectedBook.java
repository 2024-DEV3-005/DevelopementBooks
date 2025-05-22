package com.store.book.request.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectedBook {

	@Schema(example = "12345", description = "Unique Serial number of the book")
    String serialNumber;
	
	@Schema(example = "5", description = "Number of the books to order")
    Integer quantity;
}
