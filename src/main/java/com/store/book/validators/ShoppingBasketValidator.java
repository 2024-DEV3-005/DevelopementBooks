package com.store.book.validators;

import static com.store.book.constants.BookConstants.EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED;

import org.springframework.util.CollectionUtils;

import com.store.book.exception.MissingItemsInBasketException;
import com.store.book.request.model.ShoppingBasket;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ShoppingBasketValidator {

	public static void validateShoppingBasket(ShoppingBasket shoppingBasket) {
		validateBasketNotEmpty(shoppingBasket);
	}

	private static void validateBasketNotEmpty(ShoppingBasket shoppingBasket) {
		if (null == shoppingBasket || CollectionUtils.isEmpty(shoppingBasket.getSerialNumberOfBook())) {
			throw new MissingItemsInBasketException(EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED);
		}
	}

}
