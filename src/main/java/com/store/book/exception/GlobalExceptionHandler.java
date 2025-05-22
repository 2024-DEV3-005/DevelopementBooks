package com.store.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.store.book.response.model.ExceptionResponse;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleNotFoundException(Exception exception) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

    @ExceptionHandler(MissingItemsInBasketException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception exception) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
