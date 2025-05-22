package com.store.book.service.impl;

import static com.store.book.constants.BookConstants.MINIMUM_QUANTITY;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.store.book.service.PricingService;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.BookQuantityDetailSet;
import com.store.book.service.model.BookQuanityDetail;
import com.store.book.service.model.OrderSummary;

@Service("pricingService")
public class PricingServiceImpl implements PricingService {

	@Override
	public OrderSummary getOrderSummary(Basket basket) {

		return new OrderSummary(categorizeBooksByQuantityDetails(basket));
	}

	private List<BookQuantityDetailSet> categorizeBooksByQuantityDetails(Basket basket) {
		List<BookQuantityDetailSet> categorizedSetOfBooks = new ArrayList<>();
		List<BookQuanityDetail> copyOfBooksToOrder = cloneBookQuantityDetailsList(basket);
		while (!copyOfBooksToOrder.isEmpty()) {
			BookQuantityDetailSet bookSet = createBooksSetBasedOnQuantity(copyOfBooksToOrder);
			categorizedSetOfBooks.add(bookSet);
		}
		return categorizedSetOfBooks;
	}

	private List<BookQuanityDetail> cloneBookQuantityDetailsList(Basket basket) {
		return basket.getBooksToOrder().stream()
				.map(shoppingOrder -> new BookQuanityDetail(shoppingOrder.getBook(), shoppingOrder.getQuantity()))
				.collect(Collectors.toList());
	}

	private BookQuantityDetailSet createBooksSetBasedOnQuantity(List<BookQuanityDetail> copyOfBooksToOrder) {
		Set<Book> books = new HashSet<>();
		Integer maximumBookOrdered = copyOfBooksToOrder.size();

		Iterator<BookQuanityDetail> iterator = copyOfBooksToOrder.iterator();

		while (iterator.hasNext() && books.size() < maximumBookOrdered) {
			BookQuanityDetail book = iterator.next();
			books.add(book.getBook());

			if (book.getQuantity() <= MINIMUM_QUANTITY) {
				iterator.remove();
			} else {
				book.setQuantity(book.getQuantity() - MINIMUM_QUANTITY);
			}
		}
		return new BookQuantityDetailSet(books);
	}

}