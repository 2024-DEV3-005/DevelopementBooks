package com.store.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.book.mapper.ResponseObjectMapper;
import com.store.book.request.model.ShoppingBasket;
import com.store.book.response.model.OrderSummaryResponse;
import com.store.book.service.BookStoreService;
import com.store.book.service.PricingService;
import com.store.book.service.model.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/store")
public class BookStoreController {

	private final BookStoreService service;
	private final ResponseObjectMapper mapper;
	private final PricingService pricingService;

	@Autowired
	public BookStoreController(@Qualifier("bookStoreService") BookStoreService bookStoreService,
			ResponseObjectMapper mapper, @Qualifier("pricingService") PricingService pricingService) {
		this.service = bookStoreService;
		this.mapper = mapper;
		this.pricingService = pricingService;
	}

	@Operation(summary = "Retrieve the list of books from the store", description = "This endpoint returns the current BookStore configuration including the list of books.")
	@ApiResponses(value = @ApiResponse(responseCode = "200", description = "Successfully retrieved the book list"))
	@GetMapping(value = "books", produces = "application/json")
	public ResponseEntity<List<Book>> getAllBooks() {

		return ResponseEntity.status(HttpStatus.OK).body(service.getAvailableBooks());

	}

	@Operation(summary = "Fetch the price for books ", description = "Fetch the best price for the developement books ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successfully fetched the Price for the book"),
			@ApiResponse(responseCode = "404", description = "Resource Not Found") })
	@PostMapping(value = "calculatePrice", produces = "application/json")
	public ResponseEntity<OrderSummaryResponse> fetchCalculatePrice(@RequestBody ShoppingBasket basket) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(mapper.toOrderSummaryResponse(pricingService.getOrderSummary(mapper.toBasket(basket))));
	}
}
