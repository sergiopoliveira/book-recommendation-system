package com.book.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    private Book book;

    @Before
    public void setup() {
        book = new Book();
    }

    @Test
    public void testGetId() {
        Long idValue = 4L;
        book.setId(idValue);
        assertEquals(idValue, book.getId());
    }

    @Test
    public void testGetAsin() {
        String asin = "123456";
        book.setAsin(asin);
        assertEquals(asin, book.getAsin());
    }

    @Test
    public void testGetTitle() {
        String title = "Effective Java";
        book.setTitle(title);
        assertEquals(title, book.getTitle());
    }

    @Test
    public void testGetAuthor() {
        String author = "Doestoevski";
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
    }

    @Test
    public void testGetGenre() {
        String genre = "History";
        book.setGenre(genre);
        assertEquals(genre, book.getGenre());
    }


}
