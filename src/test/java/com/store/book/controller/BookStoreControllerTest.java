package com.store.book.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BookStoreControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldGet200ResponseForBookApi() throws Exception {

		mockMvc.perform(get("/store/books").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
}
