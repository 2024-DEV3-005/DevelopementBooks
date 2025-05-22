package com.store.book.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookConstants {
	
public static final String BOOK_NOT_PRESENT_TO_PROCESS = "There is no book present with Serial number: [%s]";

public static final int PERCENTAGE_DIVISOR = 100;

public static final int FIVE_PERCENTAGE_OFFER = 5;

public static final int TWO_BOOKS = 2;

public static final String EMPTY_BASKET_PLEASE_ADD_BOOKS_TO_PROCEED = "Shopping basket is empty. Please add books to proceed.";

public static final String DUPLICATE_BOOK_MESSAGE = "Duplicate Serial number [%s] found in the order! Please Remove or Update Quantity";

public static final int TEN_PERCENTAGE_OFFER = 10;

public static final int NO_DISCOUNT = 5;

public static final int THREE_BOOKS = 3;

public static final int FOUR_BOOKS = 4;

public static final int FIFTEEN_PERCENTAGE_OFFER = 15;

}
