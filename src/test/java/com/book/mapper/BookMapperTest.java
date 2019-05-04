package com.book.mapper;

import com.book.api.mapper.BookMapper;
import com.book.api.model.BookDTO;
import com.book.domain.Book;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class BookMapperTest {

    private static final String ASIN = "7444397";
    private static final String TITLE = "are to Dream: Life as One Direction";
    private static final String AUTHOR = "One Direction";
    private static final String GENRE = "One Direction";


    private BookMapper bookMapper = BookMapper.INSTANCE;

    @Test
    public void bookToBookDTOTest() {

        //given
        Book book = new Book();
        book.setAsin(ASIN);
        book.setTitle(TITLE);
        book.setAuthor(AUTHOR);
        book.setGenre(GENRE);

        // when
        BookDTO bookDTO = bookMapper.bookToBookDTO(book);

        // then
        assertEquals(ASIN, bookDTO.getAsin());
        assertEquals(TITLE, bookDTO.getTitle());
        assertEquals(AUTHOR, bookDTO.getAuthor());
        assertEquals(GENRE, bookDTO.getGenre());
    }

    @Test
    public void bookDTOToBookTest() {

        //given
        BookDTO bookDTO = new BookDTO();
        bookDTO.setAsin(ASIN);
        bookDTO.setTitle(TITLE);
        bookDTO.setAuthor(AUTHOR);
        bookDTO.setGenre(GENRE);

        // when
        Book book = bookMapper.bookDTOToBook(bookDTO);

        // then
        assertEquals(ASIN, book.getAsin());
        assertEquals(TITLE, book.getTitle());
        assertEquals(AUTHOR, book.getAuthor());
        assertEquals(GENRE, book.getGenre());
    }
}
