package com.book.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findById(Long id);
}