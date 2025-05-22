package com.store.book.service.impl;

import static com.store.book.constants.BookConstants.MINIMUM_QUANTITY;
import static com.store.book.constants.BookConstants.PERCENTAGE_DIVISOR;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.store.book.service.PricingService;
import com.store.book.service.model.Amount;
import com.store.book.service.model.Basket;
import com.store.book.service.model.Book;
import com.store.book.service.model.BookQuanityDetail;
import com.store.book.service.model.Discount;

@Service("pricingService")
public class PricingServiceImpl implements PricingService {

	@Override
	public Amount getPrice(Basket basket) {

		BigDecimal orderTotal = BigDecimal.ZERO;

		BigDecimal discountedPrice = BigDecimal.ZERO;

		Integer discount = Discount.findDiscountByNumberOfBooks(basket.getBooksToOrder().size());

		List<Set<Book>> listOfCategorizedBookSet = categorizeBooksByQuantityDetails(basket);

		return computeFinalAmountAfterDiscount(orderTotal, discountedPrice, discount, listOfCategorizedBookSet);
	}

	private List<Set<Book>> categorizeBooksByQuantityDetails(Basket basket) {
		List<Set<Book>> categorizedSetOfBooks = new ArrayList<>();
		List<BookQuanityDetail> copyOfBooksToOrder = cloneBookQuantityDetailsList(basket);
		while (!copyOfBooksToOrder.isEmpty()) {
			Set<Book> bookSet = createBooksSetBasedOnQuantity(copyOfBooksToOrder);
			categorizedSetOfBooks.add(bookSet);
		}
		return categorizedSetOfBooks;
	}

	private List<BookQuanityDetail> cloneBookQuantityDetailsList(Basket basket) {
		return basket.getBooksToOrder().stream()
				.map(shoppingOrder -> new BookQuanityDetail(shoppingOrder.getBook(), shoppingOrder.getQuantity()))
				.collect(Collectors.toList());
	}

	private Set<Book> createBooksSetBasedOnQuantity(List<BookQuanityDetail> copyOfBooksToOrder) {
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
		return books;
	}

	private Amount computeFinalAmountAfterDiscount(BigDecimal orderTotal, BigDecimal discountedPrice, Integer discount,
			List<Set<Book>> listOfCategorizedBookSet) {
		for (Set<Book> bookSet : listOfCategorizedBookSet) {
			BigDecimal totalPriceOfSet = getTotalPrice(bookSet);
			orderTotal = orderTotal.add(totalPriceOfSet);
			discountedPrice = discountedPrice.add(
					computePriceAfterDiscount(totalPriceOfSet, Discount.findDiscountByNumberOfBooks(bookSet.size())));
		}
		return new Amount(orderTotal, discountedPrice, discount);
	}

	private BigDecimal getTotalPrice(Set<Book> bookSet) {

		return bookSet.stream().map(Book::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private BigDecimal computePriceAfterDiscount(BigDecimal totalPrice, int discount) {
		BigDecimal discountRate = BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(PERCENTAGE_DIVISOR));
		return totalPrice.multiply(BigDecimal.ONE.subtract(discountRate));
	}

}