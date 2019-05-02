package com.book.service;

import com.book.api.mapper.BookMapper;
import com.book.api.mapper.UserMapper;
import com.book.api.model.BookDTO;
import com.book.bootstrap.Bootstrap;
import com.book.domain.User;
import com.book.repositories.BookRepository;
import com.book.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookServiceImplTestIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private UserService userService;

    private BookService bookService;

    @Before
    public void setUp() throws Exception {
        System.out.println("Loading User Data");
        System.out.println(userRepository.findAll().size());

        // setup data for testing
        Bootstrap bootstrap = new Bootstrap(userRepository);
        bootstrap.run(); // load data

        userService = new UserServiceImpl(UserMapper.INSTANCE, userRepository);
        bookService = new BookServiceImpl(BookMapper.INSTANCE, bookRepository, userRepository);
    }

    @Test
    public void getFirstBracketUser() throws Exception {

        // get user that falls in the first bracket
        User firstBracketUser = userRepository.getOne(1L);

        // get the list of books that are returned for that user
        List<BookDTO> listBooks = bookService.getBooksByName(firstBracketUser.getName());

        // filter books that belong to those genre for that user preferences
        long count = listBooks
                .stream()
                .filter(list -> list.getGenre().equals("Science & Math")).count();

        assertTrue(count >= 4);

    }

}
