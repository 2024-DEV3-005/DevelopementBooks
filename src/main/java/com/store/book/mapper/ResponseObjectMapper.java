package com.store.book.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import com.store.book.request.model.BookInfo;
import com.store.book.request.model.ShoppingBasket;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;

@Mapper(componentModel = "spring")
public interface ResponseObjectMapper {

	List<BookInfo> toBookInfoResponse(List<Book> books);

	default Basket toBasket(ShoppingBasket source) {
		List<Book> books = new ArrayList<>();
		for (String slNumber : source.getSerialNumberOfBook()) {
			books.add(Book.fetchBySerialNo(slNumber));
		}
		return new Basket(books);
	}

}
