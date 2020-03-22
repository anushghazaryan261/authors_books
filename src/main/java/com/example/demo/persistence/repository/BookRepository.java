package com.example.demo.persistence.repository;

import com.example.demo.persistence.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Long> {
}
