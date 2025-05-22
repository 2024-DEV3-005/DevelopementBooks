package com.store.book.controller;

import static com.store.book.constants.BookTestConstants.BOOK_INFO_TO_MATCH;
import static com.store.book.constants.BookTestConstants.GET_BOOK_API;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
}
