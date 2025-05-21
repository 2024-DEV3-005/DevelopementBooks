package com.store.book.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.store.book.service.BookStoreService;
import com.store.book.service.model.Book;

@Service("bookStoreService")
public class BookStoreServiceImpl implements BookStoreService {

	@Override
	public List<Book> getAvailableBooks() {
		return Arrays.asList(Book.values());
	}

}
