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

public static final int FIFTEEN_PERCENTAGE_OFFER = 20;

public static final int FIVE_BOOKS = 5;

public static final int TWENTY_PERCENTAGE_OFFER = 25;

public static final String SERIAL_NUMBER_MISSING_MESSAGE = "SerialNumber Missing in the request! Add Missing details and request again";

public static final String ORDER_QUANTITY_MISSING_MESSAGE = "Order quantity is Missing in the request! Add Missing details and request again";

public static final int ZERO_QUANTITY = 0;

public static final int MINIMUM_QUANTITY = 1;

public static final String DELIMITER = ",";

}
