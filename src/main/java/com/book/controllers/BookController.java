package com.book.controllers;

import com.book.api.model.BookDTO;
import com.book.api.model.BookListDTO;
import com.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(BookController.BASE_URL)
public class BookController {

    public static final String BASE_URL = "/api/books";

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{user}")
    @ResponseStatus(HttpStatus.OK)
    public BookListDTO getBooksByName(@PathVariable String user) {

        return new BookListDTO(bookService.getBooksByName(user));
    }

    @PostMapping("/{user}/{asin}/{feedback}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO updateFeedback(@PathVariable String user, @PathVariable String asin, @PathVariable String feedback) {

        return bookService.giveFeedback(user, asin, feedback);
    }


}
