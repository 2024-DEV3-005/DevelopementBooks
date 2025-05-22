package com.store.book.service.model;

import static com.store.book.constants.BookConstants.BOOK_NOT_PRESENT_TO_PROCESS;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.store.book.exception.BookNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Book {

	CLEAN_CODE("1", "Clean Code", "Robert Martin", "2008", new BigDecimal("50")),
	CLEAN_CODER("2", "The Clean Coder", "Robert Martin", "2011", new BigDecimal("50")),
	CLEAN_ARCHITECTURE("3", "Clean Architecture", "Robert Martin", "2017", new BigDecimal("50")),
	TEST_DRIVEN_DEVELOPMENT("4", "Test Driven Development by Example", "Kent Beck", "2003", new BigDecimal("50")),
	LEGACY_CODE("5", "Working Effectively With Legacy Code", "Michael C. Feathers", "2004", new BigDecimal("50"));

	final String serialNumber;
	final String title;
	final String authorName;
	final String yearOfPublish;
	final BigDecimal price;

	private static final Map<String, Book> bookMap = Stream.of(Book.values())
			.collect(Collectors.toUnmodifiableMap(Book::getSerialNumber, book -> book));

	public static Book fetchBySerialNo(String serialNumber) {
		return Optional.ofNullable(bookMap.get(serialNumber))
				.orElseThrow(() -> new BookNotFoundException(String.format(BOOK_NOT_PRESENT_TO_PROCESS, serialNumber)));
	}
}
