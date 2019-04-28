package com.book.repositories;

import com.book.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findById(Long id);

	@Query(value = "SELECT * FROM user WHERE name = :name LIMIT 1",
			nativeQuery = true)
	Optional<User> findByName(String name);
}