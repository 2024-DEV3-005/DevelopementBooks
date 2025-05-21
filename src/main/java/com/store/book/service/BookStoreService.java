package com.store.book.service;

import java.util.List;

import com.store.book.service.model.Book;

public interface BookStoreService {

	List<Book> getAvailableBooks();

}
