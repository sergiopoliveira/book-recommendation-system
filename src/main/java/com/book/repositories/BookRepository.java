package com.book.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	Optional<Book> findById(Long id);
}