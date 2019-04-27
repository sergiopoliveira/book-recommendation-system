package com.book.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import com.book.api.model.BookDTO;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.book.service.BookService;

public class BookControllerTest {

	@Mock
	BookService bookService;

	@InjectMocks
	BookController bookController;

	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(bookController)
				.setControllerAdvice(new RestResponseEntityExceptionHandler())
				.build();
}
	
	@Test
	public void testGetBooksByName() throws Exception {
		
		BookDTO book1 = new BookDTO();
		book1.setAsin("1234567");
		book1.setAuthor("Dostoievski");
		book1.setGenre("Romance");
		book1.setTitle("Crime and Punishment");
		
		BookDTO book2 = new BookDTO();
		book2.setAsin("4567891");
		book2.setAuthor("Thoreau");
		book2.setGenre("Romance");
		book2.setTitle("Walden, or Life in the Woods");
		
		List<BookDTO> listBooks =  Arrays.asList(book1, book2);
		
		when(bookService.getBooksByName("Adam")).thenReturn(listBooks);
		
		mockMvc.perform(get(BookController.BASE_URL + "/Adam")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.books", hasSize(2)));
	
	}
		
	
	@Test
	public void testUpdateFeedback() throws Exception {
		BookDTO book1 = new BookDTO();
		book1.setAsin("1234567");
		book1.setAuthor("Dostoievski");
		book1.setGenre("Romance");
		book1.setTitle("Crime and Punishment");
		
		when(bookService.giveFeedback("Adam","123145", "1")).thenReturn(book1);
		
		mockMvc.perform(post(BookController.BASE_URL + "/Adam/123145/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.genre", equalTo("Romance")))
				.andExpect(jsonPath("$.title", equalTo("Crime and Punishment")))
				.andExpect(jsonPath("$.author", equalTo("Dostoievski")));
	}
	
	
	@Test
	public void testErrorURLFormat() throws Exception {
		
		//malformed URL
		mockMvc.perform(get(BookController.BASE_URL + "/AAA/BBB")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
} 
}