package com.example.demo.rest.controller;

import com.example.demo.persistence.model.Author;
import com.example.demo.persistence.model.Book;
import com.example.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @PostMapping(value = "/book")
    public void create(@RequestBody Book book){
        bookService.create(book);
    }


    @PutMapping(value = "/book/{id}")
    public ResponseEntity<Book> update(@RequestBody Book book, @PathVariable Long id){
        Book update = bookService.update(id, book);
        if(book!=null){
            return ResponseEntity.ok(update);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @GetMapping(value = "/book/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id){
        Book byId = bookService.getById(id);
        if(byId!=null){
            return ResponseEntity.ok(byId);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping(value = "/book")
    public ResponseEntity<List<Book>> getAll(){
        List<Book> all = bookService.getAll();
        if(all!=null){
            return ResponseEntity.ok(all);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(value = "/authors/{id}")
    public ResponseEntity<Set<Author>> getAllAuthors(@PathVariable Long id){
        Set<Author> allAuthorsOfTheBookWithId = bookService.getAllAuthorsOfTheBookWithId(id);
        if(allAuthorsOfTheBookWithId!=null){
            return ResponseEntity.ok(allAuthorsOfTheBookWithId);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @DeleteMapping(value = "/book/{id}")
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }
}
