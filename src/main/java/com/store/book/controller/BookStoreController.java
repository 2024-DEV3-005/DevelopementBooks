package com.store.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.book.mapper.ResponseObjectMapper;
import com.store.book.request.model.BookInfo;
import com.store.book.service.BookStoreService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/store")
public class BookStoreController {

	private final BookStoreService service;
	private final ResponseObjectMapper mapper;

	@Autowired
	public BookStoreController(@Qualifier("bookStoreService") BookStoreService bookStoreService,
			ResponseObjectMapper mapper) {
		this.service = bookStoreService;
		this.mapper = mapper;
	}

	@Operation(summary = "Retrieve the list of books from the store", description = "This endpoint returns the current BookStore configuration including the list of books.")
	@ApiResponses(value = @ApiResponse(responseCode = "200", description = "Successfully retrieved the book list"))
	@GetMapping(value = "books", produces = "application/json")
	public ResponseEntity<List<BookInfo>> getAllBooks() {

		return ResponseEntity.status(HttpStatus.OK).body(mapper.toBookInfoResponse(service.getAvailableBooks()));

	}
}
