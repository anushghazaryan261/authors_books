package com.example.demo.service;

import com.example.demo.persistence.model.Author;
import com.example.demo.persistence.model.Book;
import com.example.demo.persistence.repository.AuthorRepository;
import com.example.demo.persistence.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class BookService {
    private static final Logger LOGGER= LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book create(Book book){
        LOGGER.info("Creating book " + book);
        return bookRepository.save(book);
    }

    public Book getById(Long id){
        LOGGER.info("Getting book with id " + id);
        List<Book> all = getAll();
        for (Book book : all) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
    public List<Book> getAll(){
        LOGGER.info("Getting all books");
        return bookRepository.findAll();
    }

    public Set<Author> getAllAuthorsOfTheBookWithId(Long id){
        LOGGER.info("Requesting to get the authors of the book with id " + id);
        Book byId = getById(id);
        if(byId!=null){
            if(byId.getAuthors()!=null){
                LOGGER.info("Successfully found the authors");
                return byId.getAuthors();
            }
            else{
                LOGGER.info("Failed to find authors");
                return null;
            }
        }else{
            return null;
        }
    }
    public Book update(Long id,Book book){
        LOGGER.info("Requesting to update book with id " + id);
        Book byId = getById(id);
        if(byId!=null&&book!=null){
            LOGGER.info("Successfully updated the book");
            byId.setTitle(book.getTitle());
            byId.setNumberOfPages(book.getNumberOfPages());
            byId.setAuthors(book.getAuthors());
            return bookRepository.save(byId);
        }else{
            LOGGER.info("Failed to update the book");
            return null;
        }
    }
    public void delete(Long id){
        LOGGER.info("Requesting to delete the book with id " + id);
        if(getById(id)!=null){
            LOGGER.info("Successfully deleted the book");
            bookRepository.deleteById(id);
        }else{
            LOGGER.info("Failed to delete the book");
        }
    }
}
