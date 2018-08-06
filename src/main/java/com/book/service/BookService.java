package com.book.service;

import java.util.List;

import com.book.domain.Book;

public interface BookService {

	List<Book> getBooksByName(String name);
}
