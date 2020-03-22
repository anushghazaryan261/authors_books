package com.example.demo.rest.controller;

import com.example.demo.persistence.model.Author;
import com.example.demo.persistence.model.Book;
import com.example.demo.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class AuthorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @PostMapping(value = "/author")
    public void create(@RequestBody Author author){
        authorService.create(author);
    }


    @PutMapping(value = "/author/{id}")
    public ResponseEntity<Author> update(@RequestBody Author author, @PathVariable Long id){
        Author update = authorService.update(id, author);
        if(author!=null){
            return ResponseEntity.ok(update);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @GetMapping(value = "/author/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id){
        Author byId = authorService.getById(id);
        if(byId!=null){
            return ResponseEntity.ok(byId);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @GetMapping(value = "/author")
    public ResponseEntity<List<Author>> getAll(){
        List<Author> all = authorService.getAll();
        if(all!=null){
            return ResponseEntity.ok(all);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping(value = "/books/{id}")
    public ResponseEntity<Set<Book>> getAllBooks(@PathVariable Long id){
        Set<Book> allBooksOfTheAuthorWithId = authorService.getAllBooksOfTheAuthorWithId(id);
        if(allBooksOfTheAuthorWithId!=null){
            return ResponseEntity.ok(allBooksOfTheAuthorWithId);
        }else{
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @DeleteMapping(value = "/author/{id}")
    public void delete(@PathVariable Long id){
        authorService.delete(id);
    }
}
