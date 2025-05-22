package com.store.book.controller;

import static com.store.book.constants.BookTestConstants.BASKET_WITH_ONE_BOOK;
import static com.store.book.constants.BookTestConstants.BOOK_INFO_TO_MATCH;
import static com.store.book.constants.BookTestConstants.EMPTY_BASKET_REQUEST;
import static com.store.book.constants.BookTestConstants.EMPTY_SHOPPING_BASKET_ERROR;
import static com.store.book.constants.BookTestConstants.GET_BOOK_API;
import static com.store.book.constants.BookTestConstants.NO_BOOK_PRESENT_WITH_GIVEN_SERIAL_NUMBER;
import static com.store.book.constants.BookTestConstants.PRICE_API;
import static com.store.book.constants.BookTestConstants.UNDEFINED_SERIAL_NUMBER_OF_BOOK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.store.book.mapper.ResponseObjectMapper;
import com.store.book.service.impl.BookStoreServiceImpl;
import com.store.book.service.model.Book;

@SpringBootTest
@AutoConfigureMockMvc
class BookStoreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ResponseObjectMapper mapper;

	@MockitoBean
	private BookStoreServiceImpl bookStoreService;

	@Test
	void shouldGet200ResponseForBookApi() throws Exception {

		mockMvc.perform(get(GET_BOOK_API).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void shouldGetAllTheAvailableBookList() throws Exception {
		List<Book> mockBooks = List.of(Book.values());

		Mockito.when(bookStoreService.getAvailableBooks()).thenReturn(mockBooks);

		mockMvc.perform(get(GET_BOOK_API).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(BOOK_INFO_TO_MATCH));
	}

	@Test
	void shouldGet200ResponseForCalculatePriceApi() throws Exception {

		mockMvc.perform(post(PRICE_API).content(BASKET_WITH_ONE_BOOK).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	void shouldGet404ErrorWhenBookNotPresentWithGivenSerialNumber() throws Exception {
		mockMvc.perform(
				post(PRICE_API).content(UNDEFINED_SERIAL_NUMBER_OF_BOOK).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isNotFound()).andExpect(content().string(NO_BOOK_PRESENT_WITH_GIVEN_SERIAL_NUMBER));
	}

	@Test
	void shouldGet400ResponseWhenEmptyRequestPassedForCalculatePriceApi() throws Exception {
		mockMvc.perform(post(PRICE_API).content(EMPTY_BASKET_REQUEST).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andExpect(content().string(EMPTY_SHOPPING_BASKET_ERROR));
	}

}
