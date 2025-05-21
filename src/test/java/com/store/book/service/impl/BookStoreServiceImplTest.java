package com.store.book.service.impl;

import static org.junit.jupiter.api.Assertions.*;

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
			assertFalse(availableBooks.isEmpty(), "Book list should not be empty");
			assertEquals(5, availableBooks.size(), "Should return all available books");
			assertEquals(List.of(Book.values()), availableBooks, "Returned books should match enum values");
		});

	}

}
