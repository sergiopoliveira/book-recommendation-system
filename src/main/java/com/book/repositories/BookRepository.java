package com.book.repositories;

import com.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>{

	Optional<Book> findById(Long id);
	
	@Query(value = "SELECT * FROM book ORDER BY RAND() limit :limit", 
			  nativeQuery = true)
	List<Book> getRandomBooks(@Param("limit") String limit);
	
	@Query(value = "SELECT * FROM book \r\n" + 
			"WHERE genre in (SELECT feedback_key FROM USER_FEEDBACK WHERE USER_FEEDBACK.user_id = :id and feedback>0) \r\n" + 
			"ORDER BY RAND() limit :limit", 
			  nativeQuery = true)
	List<Book> getBooksOfGenre(@Param("id") String id,
            @Param("limit") String limit);

	@Query(value = "SELECT * FROM book WHERE asin = :asin LIMIT 1",
			nativeQuery = true)
	Optional<Book> findByAsin(String asin);
}