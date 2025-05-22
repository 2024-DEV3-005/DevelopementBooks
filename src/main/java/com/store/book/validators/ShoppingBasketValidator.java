package com.store.book.validators;

import static com.store.book.constants.BookConstants.DUPLICATE_BOOK_MESSAGE;
import static com.store.book.constants.BookConstants.EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED;
import static com.store.book.constants.BookConstants.ORDER_QUANTITY_MISSING_MESSAGE;
import static com.store.book.constants.BookConstants.SERIAL_NUMBER_MISSING_MESSAGE;
import static com.store.book.constants.BookConstants.ZERO_QUANTITY;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.store.book.exception.DuplicateBookEntryException;
import com.store.book.exception.InCompleteDataException;
import com.store.book.exception.MissingItemsInBasketException;
import com.store.book.request.model.SelectedBook;
import com.store.book.request.model.ShoppingBasket;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShoppingBasketValidator {

	public static void validateShoppingBasket(ShoppingBasket shoppingBasket) {
		validateBasketNotEmpty(shoppingBasket);
		checkForDuplicateSerialNos(shoppingBasket);
		checkMandatorySerialNumberInSelectedBooks(shoppingBasket);
		checkMandatoryQuantityInSelectedBooks(shoppingBasket);
	}

	private static void validateBasketNotEmpty(ShoppingBasket shoppingBasket) {
		if (null == shoppingBasket || CollectionUtils.isEmpty(shoppingBasket.getSelectedBooks())) {
			throw new MissingItemsInBasketException(EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED);
		}
	}

	private static void checkForDuplicateSerialNos(ShoppingBasket shoppingBasket) {
		String duplicateEntries = findDuplicateSerialNos(shoppingBasket);
		if (StringUtils.isNotBlank(duplicateEntries)) {
			throw new DuplicateBookEntryException(String.format(DUPLICATE_BOOK_MESSAGE, duplicateEntries));

		}
	}

	private static String findDuplicateSerialNos(ShoppingBasket shoppingBasket) {
		return shoppingBasket.getSelectedBooks().stream().map(SelectedBook::getSerialNumber)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.joining(","));
	}

	private static void checkMandatorySerialNumberInSelectedBooks(ShoppingBasket shoppingBasket) {
		for (SelectedBook bookSelected : shoppingBasket.getSelectedBooks()) {
			if (StringUtils.isBlank(bookSelected.getSerialNumber())) {
				throw new InCompleteDataException(SERIAL_NUMBER_MISSING_MESSAGE);
			}
		}
	}

	private static void checkMandatoryQuantityInSelectedBooks(ShoppingBasket shoppingBasket) {
		for (SelectedBook bookSelected : shoppingBasket.getSelectedBooks()) {
			if (null == bookSelected.getQuantity() || ZERO_QUANTITY == bookSelected.getQuantity()) {
				throw new InCompleteDataException(ORDER_QUANTITY_MISSING_MESSAGE);
			}
		}
	}

}
