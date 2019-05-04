package com.book.service;

import com.book.api.mapper.BookMapper;
import com.book.api.model.BookDTO;
import com.book.domain.Book;
import com.book.domain.User;
import com.book.enums.Bracket;
import com.book.exceptions.InvalidParameterException;
import com.book.repositories.BookRepository;
import com.book.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository, UserRepository userRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BookDTO> getBooksByName(String name) {

        // find user
        User userFound = findUserByName(name);

        // find bracket
        int sum = 0;
        for (int i : userFound.getFeedback().values()) {
            sum += i;
        }

        Bracket bracket = Bracket.calculateBracket(sum);

        // generate 20 books based on feedback Map
        List<Book> listBooks = new ArrayList<>();

        if (bracket == Bracket.FIRST) {

            // 14 books will be randomly selected
            listBooks.addAll(bookRepository.getRandomBooks("14"));

            // 6 books will be selected based on feedback
            listBooks.addAll(bookRepository.getBooksOfGenre(userFound.getId().toString(), "6"));
        } else if (bracket == Bracket.SECOND) {

            // 10 books will be randomly selected
            listBooks.addAll(bookRepository.getRandomBooks("10"));

            // 10 books will be selected based on feedback
            listBooks.addAll(bookRepository.getBooksOfGenre(userFound.getId().toString(), "10"));
        } else if (bracket == Bracket.THIRD){

            // 4 books will be randomly selected
            listBooks.addAll(bookRepository.getRandomBooks("4"));

            // 16 books will be selected based on feedback
            listBooks.addAll(bookRepository.getBooksOfGenre(userFound.getId().toString(), "16"));
        }

        //return list of books
        return listBooks
                .stream()
                .map(bookMapper::bookToBookDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO giveFeedback(String name, String asin, String feedback) {

        // check if feedback is -1, 0 or 1
        if (!(("-1".equals(feedback)) || ("0".equals(feedback)) || ("1".equals(feedback)))) {
            throw new InvalidParameterException("Feedback not in [-1:1] range");
        }

        // get book genre
        String genre = findBookByAsin(asin).getGenre();

        // add feedback to that user's feedback HashMap
        User userFound = findUserByName(name);
        Integer feedbackInt = userFound.getFeedback().putIfAbsent(genre, Integer.parseInt(feedback));

        /*
         * If the specified key is not already associated with a value (or is mapped to null)
         * associates it with the given value and returns null, else returns the current value.
         */
        if (feedbackInt != null) {
            int sum = feedbackInt + Integer.parseInt(feedback);
            userFound.getFeedback().put(genre, sum);
        }

        // save User and associated feedback Map to repository
        userRepository.save(userFound);

        // return BookDTO
        return bookMapper.bookToBookDTO(findBookByAsin(asin));
    }

    private User findUserByName(String name) {
        return userRepository
                .findByName(name)
                .orElseThrow(() -> new InvalidParameterException("User does not exist"));
    }

    private Book findBookByAsin(String asin) {
        return bookRepository
                .findByAsin(asin)
                .orElseThrow(() -> new InvalidParameterException("Book does not exist"));
    }

}