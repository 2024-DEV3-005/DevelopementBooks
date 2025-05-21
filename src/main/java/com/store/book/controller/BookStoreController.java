package com.store.book.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/store")
public class BookStoreController {

	@Operation(summary = "Retrieve the list of books from the store", description = "This endpoint returns the current BookStore configuration including the list of books.")
	@ApiResponses(value = @ApiResponse(responseCode = "200", description = "Successfully retrieved the book list"))
	@GetMapping(value = "books", produces = "application/json")
	public ResponseEntity<String> getAllBooks() {

		return ResponseEntity.status(HttpStatus.OK).body("");
	}
}
