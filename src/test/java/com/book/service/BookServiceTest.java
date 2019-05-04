package com.book.service;

import com.book.api.mapper.BookMapper;
import com.book.api.model.BookDTO;
import com.book.domain.Book;
import com.book.domain.User;
import com.book.repositories.BookRepository;
import com.book.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private static final Long ID = 1L;
    private static final String GENRE = "Romance";
    private static final String RANDOM_GENRE = "History";
    private static final String NAME = "Adam";
    private static final String EMAIL = "foo@bar.com";
    public static final int TOTAL_BOOKS = 20;

    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        bookService = new BookServiceImpl(BookMapper.INSTANCE, bookRepository, userRepository);
    }

    @Test
    public void getBooksByNameUserFirstBracket() {

        final int NUM_FAV_BOOKS = 6;
        final int NUM_RANDOM_BOOKS = 14;

        // given
        List<Book> listBooksOfGenre = createListBooksOfGenre(NUM_FAV_BOOKS);
        List<Book> listRandomBooks = createListRandomBooks(NUM_RANDOM_BOOKS);
        User user = createUserFirstBracket();

        when(userRepository.findByName(anyString())).thenReturn(Optional.of(user));
        when(bookRepository.getBooksOfGenre(anyString(), anyString())).thenReturn(listBooksOfGenre);
        when(bookRepository.getRandomBooks(anyString())).thenReturn(listRandomBooks);

        // when
        List<BookDTO> bookDTOs = bookService.getBooksByName(anyString());

        // then
        verify(userRepository, times(1)).findByName(anyString());
        assertEquals(TOTAL_BOOKS, bookDTOs.size());
        assertEquals(NUM_FAV_BOOKS, bookDTOs.stream().filter(b -> b.getGenre().equals(GENRE)).count());
        assertEquals(NUM_RANDOM_BOOKS, bookDTOs.stream().filter(b -> b.getGenre().equals(RANDOM_GENRE)).count());
    }

    @Test
    public void getBooksByNameUserSecondBracket() {

        final int NUM_FAV_BOOKS = 10;
        final int NUM_RANDOM_BOOKS = 10;

        // given
        List<Book> listBooksOfGenre = createListBooksOfGenre(10);
        List<Book> listRandomBooks = createListRandomBooks(10);
        User user = createUserSecondBracket();

        when(userRepository.findByName(anyString())).thenReturn(Optional.of(user));
        when(bookRepository.getBooksOfGenre(anyString(), eq(Integer.toString(NUM_FAV_BOOKS)))).thenReturn(listBooksOfGenre);
        when(bookRepository.getRandomBooks(eq(Integer.toString(NUM_RANDOM_BOOKS)))).thenReturn(listRandomBooks);

        // when
        List<BookDTO> bookDTOs = bookService.getBooksByName(anyString());

        // then
        verify(userRepository, times(1)).findByName(anyString());
        assertEquals(TOTAL_BOOKS, bookDTOs.size());
        assertEquals(NUM_FAV_BOOKS, bookDTOs.stream().filter(b -> b.getGenre().equals(GENRE)).count());
        assertEquals(NUM_RANDOM_BOOKS, bookDTOs.stream().filter(b -> b.getGenre().equals(RANDOM_GENRE)).count());
    }

    @Test
    public void getBooksByNameUserThirdBracket() {

        final int NUM_FAV_BOOKS = 16;
        final int NUM_RANDOM_BOOKS = 4;

        // given
        List<Book> listBooksOfGenre = createListBooksOfGenre(NUM_FAV_BOOKS);
        List<Book> listRandomBooks = createListRandomBooks(NUM_RANDOM_BOOKS);
        User user = createUserThirdBracket();

        when(userRepository.findByName(anyString())).thenReturn(Optional.of(user));
        when(bookRepository.getBooksOfGenre(anyString(), eq(Integer.toString(NUM_FAV_BOOKS)))).thenReturn(listBooksOfGenre);
        when(bookRepository.getRandomBooks(eq(Integer.toString(NUM_RANDOM_BOOKS)))).thenReturn(listRandomBooks);

        // when
        List<BookDTO> bookDTOs = bookService.getBooksByName(anyString());

        // then
        verify(userRepository, times(1)).findByName(anyString());
        assertEquals(TOTAL_BOOKS, bookDTOs.size());
        assertEquals(NUM_FAV_BOOKS, bookDTOs.stream().filter(b -> b.getGenre().equals(GENRE)).count());
        assertEquals(NUM_RANDOM_BOOKS, bookDTOs.stream().filter(b -> b.getGenre().equals(RANDOM_GENRE)).count());
    }

    @Test
    public void giveFeedback() {
    }

    private List<Book> createListBooksOfGenre(int size) {

        List<Book> listBooksOfGenre = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Book book = new Book();
            book.setGenre(GENRE);
            listBooksOfGenre.add(book);
        }
        return listBooksOfGenre;
    }

    private List<Book> createListRandomBooks(int size) {
        List<Book> randomBooks = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Book book = new Book();
            book.setGenre(RANDOM_GENRE);
            randomBooks.add(book);
        }
        return randomBooks;
    }

    private User createUserFirstBracket() {
        User user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setEmail(EMAIL);
        return user;
    }

    private User createUserSecondBracket() {
        User user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setFeedback(Map.of("History", 100));
        return user;
    }

    private User createUserThirdBracket() {
        User user = new User();
        user.setId(ID);
        user.setName(NAME);
        user.setEmail(EMAIL);
        user.setFeedback(Map.of("History", 400));
        return user;
    }

}