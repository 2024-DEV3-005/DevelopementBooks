package com.store.book.constants;

public class BookTestConstants {

	public static final String BOOK_INFO_TO_MATCH = "[{\"serialNumber\":\"1\",\"title\":\"Clean Code\",\"authorName\":\"Robert Martin\",\"yearOfPublish\":\"2008\",\"price\":\"50\"},{\"serialNumber\":\"2\",\"title\":\"The Clean Coder\",\"authorName\":\"Robert Martin\",\"yearOfPublish\":\"2011\",\"price\":\"50\"},{\"serialNumber\":\"3\",\"title\":\"Clean Architecture\",\"authorName\":\"Robert Martin\",\"yearOfPublish\":\"2017\",\"price\":\"50\"},{\"serialNumber\":\"4\",\"title\":\"Test Driven Development by Example\",\"authorName\":\"Kent Beck\",\"yearOfPublish\":\"2003\",\"price\":\"50\"},{\"serialNumber\":\"5\",\"title\":\"Working Effectively With Legacy Code\",\"authorName\":\"Michael C. Feathers\",\"yearOfPublish\":\"2004\",\"price\":\"50\"}]";

	public static final int PRICE_FOR_THE_BOOK = 50;

	public static final String SERIAL_NO_FOR_FIRST_BOOK = "1";

	public static final String SHOULD_MATCH_ENUM_VALUES = "Returned books should match enum values";
	
	public static final String SHOULD_RETURN_ALL_BOOKS = "Should return all available books";
	
	public static final String SHOULD_NOT_BE_EMPTY = "Book list should not be empty";
	
	public static final String GET_BOOK_API = "/store/books";

}
