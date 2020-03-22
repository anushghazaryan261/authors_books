package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Long> {
}
