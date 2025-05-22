package com.store.book.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;

import com.store.book.request.model.BookInfo;
import com.store.book.request.model.SelectedBook;
import com.store.book.request.model.ShoppingBasket;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.BookQuanityDetail;
import com.store.book.validators.ShoppingBasketValidator;

@Mapper(componentModel = "spring")
public interface ResponseObjectMapper {

	List<BookInfo> toBookInfoResponse(List<Book> books);

	default Basket toBasket(ShoppingBasket source) {

		ShoppingBasketValidator.validateShoppingBasket(source);

		List<BookQuanityDetail> booksToOrder = new ArrayList<>();
		for (SelectedBook order : source.getSelectedBooks()) {
			booksToOrder.add(new BookQuanityDetail(Book.fetchBySerialNo(order.getSerialNumber()), order.getQuantity()));
		}
		return new Basket(booksToOrder);
	}

}
