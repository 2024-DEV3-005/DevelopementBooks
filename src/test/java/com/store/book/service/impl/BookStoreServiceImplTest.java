package com.store.book.service.impl;

import static com.store.book.constants.BookTestConstants.SHOULD_MATCH_ENUM_VALUES;
import static com.store.book.constants.BookTestConstants.SHOULD_NOT_BE_EMPTY;
import static com.store.book.constants.BookTestConstants.SHOULD_RETURN_ALL_BOOKS;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.store.book.service.BookStoreService;
import com.store.book.service.model.Book;

@ExtendWith(SpringExtension.class)
class BookStoreServiceImplTest {

	
	private final BookStoreService bookStoreService = new BookStoreServiceImpl();

	@Test
	void ShouldReturnAllThe5BooksAvailable() { 

		List<Book> availableBooks = bookStoreService.getAvailableBooks();

		assertAll(() -> {
			assertFalse(availableBooks.isEmpty(), SHOULD_NOT_BE_EMPTY);
			assertEquals(5, availableBooks.size(), SHOULD_RETURN_ALL_BOOKS);
			assertEquals(List.of(Book.values()), availableBooks, SHOULD_MATCH_ENUM_VALUES);
		});

	}

}
