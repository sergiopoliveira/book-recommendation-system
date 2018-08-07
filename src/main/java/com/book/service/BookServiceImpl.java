package com.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.book.api.mapper.BookMapper;
import com.book.api.model.BookDTO;
import com.book.domain.Book;
import com.book.domain.User;
import com.book.exceptions.InvalidParameterException;
import com.book.repositories.BookRepository;
import com.book.repositories.UserRepository;

@Service
public class BookServiceImpl implements BookService{

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
		
		// check if user exits
		if(checkUser(name) == 0) {
			throw new InvalidParameterException("User does not exist");
			}
		
		// find user
		User userFound = findUser(name);
		
		// find bracket
		int sum = 0; 
		for (int i : userFound.getFeedback().values()) {
				sum += i;
			}
		
		/*
		 * 1. First bracket - if total values added <50, only 30% of recommended books
		 * reflect those genres preference, rest is random; 
		 * 2. Second bracket - if total
		 * values added >=50 and <150 then system recommends 50% of books on t, rest
		 * random;
		 *  3. Third bracket - if total values added >=150 then system recommends
		 * 80% books on those genres, rest random.
		 * 
		 */
		
		String bracket = "";
		
		if(sum<50) bracket = "First";
		if(sum>=50 && sum<150) bracket = "Second";
		if(sum>=150) bracket = "Third";
		
		// generate 20 books based on feedback Map
		List<Book> listBooks = new ArrayList<Book>();
		
		if(bracket.equals("First")) {
			
		// 14 books will be randomly selected
		listBooks.addAll(bookRepository.getRandomBooks("14"));
		
		// 6 books will be selected based on feedback
		listBooks.addAll(bookRepository.getBooksOfGenre(userFound.getId().toString(), "6"));
		}
		
		else if(bracket.equals("Second")) {

		// 10 books will be randomly selected
		listBooks.addAll(bookRepository.getRandomBooks("10"));
		
		// 10 books will be selected based on feedback
		listBooks.addAll(bookRepository.getBooksOfGenre(userFound.getId().toString(), "10"));
		}
		
		else if(bracket.equals("Third")) {

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
		
		// check if username exists
		if(checkUser(name) == 0) {
			throw new InvalidParameterException("User does not exist");
			}
		
		// check if book exists
		if(checkBook(asin) == 0) {
			throw new InvalidParameterException("Book ASIN does not exist");
			}
		
		// check if feedback is -1, 0 or 1
		if(!((feedback.equals("-1")) || (feedback.equals("0")) || (feedback.equals("1")))) {
			throw new InvalidParameterException("Feedback not in [-1:1] range");
			}
		
		// get book genre
		String genre = "";
		genre = findBook(asin).getGenre();
				
		// add feedback to that user's feedback HashMap
		User userFound = findUser(name);
		Integer feedbackInt = userFound.getFeedback().putIfAbsent(genre, Integer.parseInt(feedback));
		
		/*
		 * If the specified key is not already associated with a value (or is mapped to null) 
		 * associates it with the given value and returns null, else returns the current value.
		 */
		if(feedbackInt != null) {
			int sum = feedbackInt.intValue() + Integer.parseInt(feedback);
			userFound.getFeedback().put(genre, sum);
		}
		
		// save User and associated feedback Map to repository
		userRepository.save(userFound);

		// return BookDTO
		BookDTO bookDTO = bookMapper.bookToBookDTO(findBook(asin));
		return bookDTO;
	}
	
	
	private long checkUser(String name) {
		
		return userRepository.findAll()
		.stream()
		.filter(user -> user.getName().equals(name))
		.count();
	}
	
	private long checkBook(String asin) {
		
		return bookRepository.findAll()
		.stream()
		.filter(book -> book.getAsin().equals(asin))
		.count();
	}
	
	private User findUser(String name) {
		
		return userRepository.findAll()
		.stream()
		.filter(user -> user.getName().equals(name))
		.findFirst().get();
	}
	
	private Book findBook(String asin) {
		
		return bookRepository.findAll()
		.stream()
		.filter(book -> book.getAsin().equals(asin))
		.findFirst().get();
	}
		
		
}


