package com.book.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.book.api.model.BookListDTO;
import com.book.service.BookService;

@RestController
@RequestMapping(BookController.BASE_URL)
public class BookController {

public static final String BASE_URL = "/api/books";
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
//	@GetMapping
//	@ResponseStatus(HttpStatus.OK)
//	public BookListDTO getBooksByName(@PathVariable String name) {
//		
//		return new BookListDTO(bookService.getBooksByName(name));
//}
//	

//	@PutMapping("/{user}/{asin}/{feedback}")
//	@ResponseStatus(HttpStatus.OK)
//	public FeedbackDTO updateFeedback(@PathVariable String user, @PathVariable String asin, @PathVariable String feedback) {
//
//		return bookService.updateFeedback(user, asin, feedback);
//	}

}
