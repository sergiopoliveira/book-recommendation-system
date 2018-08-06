package com.book.api.model;

import lombok.Data;

@Data
public class BookDTO {

	private String asin;
	private String title;
	private String author;
	private String genre;
}
