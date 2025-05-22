package com.store.book.controller;

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

	public static final String BOOK_INFO_TO_MATCH = "[{\"serialNumber\":\"1\",\"title\":\"Clean Code\",\"authorName\":\"Robert Martin\",\"yearOfPublish\":\"2008\",\"price\":\"50\"},{\"serialNumber\":\"2\",\"title\":\"The Clean Coder\",\"authorName\":\"Robert Martin\",\"yearOfPublish\":\"2011\",\"price\":\"50\"},{\"serialNumber\":\"3\",\"title\":\"Clean Architecture\",\"authorName\":\"Robert Martin\",\"yearOfPublish\":\"2017\",\"price\":\"50\"},{\"serialNumber\":\"4\",\"title\":\"Test Driven Development by Example\",\"authorName\":\"Kent Beck\",\"yearOfPublish\":\"2003\",\"price\":\"50\"},{\"serialNumber\":\"5\",\"title\":\"Working Effectively With Legacy Code\",\"authorName\":\"Michael C. Feathers\",\"yearOfPublish\":\"2004\",\"price\":\"50\"}]";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ResponseObjectMapper mapper;

	@MockitoBean
	private BookStoreServiceImpl bookStoreService;

	@Test
	void shouldGet200ResponseForBookApi() throws Exception {

		mockMvc.perform(get("/store/books").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	void shouldGetAllTheAvailableBookList() throws Exception {
		List<Book> mockBooks = List.of(Book.values());

		Mockito.when(bookStoreService.getAvailableBooks()).thenReturn(mockBooks);

		mockMvc.perform(get("/store/books").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().json(BOOK_INFO_TO_MATCH));
	}
}
