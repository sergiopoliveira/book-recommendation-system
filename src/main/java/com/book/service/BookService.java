package com.book.service;

import java.util.List;

import com.book.api.model.BookDTO;

public interface BookService {

	List<BookDTO> getBooksByName(String name);
	
	BookDTO giveFeedback(String name, String asin, String feedback);
	
}
