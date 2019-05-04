package com.book.controllers;

import com.book.api.model.BookDTO;
import com.book.exceptions.InvalidParameterException;
import com.book.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookControllerTest {

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
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

        List<BookDTO> listBooks = Arrays.asList(book1, book2);

        when(bookService.getBooksByName("Adam")).thenReturn(listBooks);

        mockMvc.perform(get(BookController.BASE_URL + "/Adam")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.books", hasSize(2)));

    }

    @Test
    public void testGetBooksByNameNotFound() throws Exception {

        when(bookService.getBooksByName(anyString())).thenThrow(InvalidParameterException.class);

        mockMvc.perform(get(BookController.BASE_URL + "/Foo")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateFeedback() throws Exception {
        BookDTO book1 = new BookDTO();
        book1.setAsin("1234567");
        book1.setAuthor("Dostoievski");
        book1.setGenre("Romance");
        book1.setTitle("Crime and Punishment");

        when(bookService.giveFeedback("Adam", "123145", "1")).thenReturn(book1);

        mockMvc.perform(post(BookController.BASE_URL + "/Adam/123145/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.genre", equalTo("Romance")))
                .andExpect(jsonPath("$.title", equalTo("Crime and Punishment")))
                .andExpect(jsonPath("$.author", equalTo("Dostoievski")));
    }

    @Test
    public void testUpdateIncorrectFeedback() throws Exception {
        BookDTO book1 = new BookDTO();
        book1.setAsin("1234567");
        book1.setAuthor("Dostoievski");
        book1.setGenre("Romance");
        book1.setTitle("Crime and Punishment");

        when(bookService.giveFeedback("Adam", "123145", "4")).thenThrow(InvalidParameterException.class);

        mockMvc.perform(post(BookController.BASE_URL + "/Adam/123145/4")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
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
