package com.example.demo.service;

import com.example.demo.persistence.model.Author;
import com.example.demo.persistence.model.Book;
import com.example.demo.persistence.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    private static final Logger LOGGER= LoggerFactory.getLogger(AuthorService.class);

   private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author create(Author author){
        LOGGER.info("Creating author " + author);
        return authorRepository.save(author);
    }

    public Author getById(Long id){
        LOGGER.info("Getting author with id " + id);
        List<Author> all = getAll();
        for (Author author : all) {
            if (author.getId().equals(id)) {
                return author;
            }
        }
        return null;
    }
    public List<Author> getAll(){
        LOGGER.info("Getting all authors");
        return authorRepository.findAll();
    }

    public Set<Book> getAllBooksOfTheAuthorWithId(Long id){
        LOGGER.info("Requesting to get the books of the author with id " + id);
        Author byId = getById(id);
        if(byId!=null){
            if(byId.getBooks()!=null){
                LOGGER.info("Successfully found the books");
                return byId.getBooks();
            }
            else{
                LOGGER.info("Failed to find books");
                return null;
            }
        }else{
            return null;
        }
    }
    public Author update(Long id,Author author){
        LOGGER.info("Requesting to update author with id " + id);
        Author byId = getById(id);
        if(byId!=null&&author!=null){
            LOGGER.info("Successfully updated the author");
            byId.setName(author.getName());
            byId.setSurname(author.getSurname());
            byId.setBooks(author.getBooks());
            return authorRepository.save(byId);
        }else{
            LOGGER.info("Failed to update the author");
            return null;
        }
    }
    public void delete(Long id){
        LOGGER.info("Requesting to delete author with id " + id);
        if(getById(id)!=null){
            LOGGER.info("Successfully deleted the author");
            authorRepository.deleteById(id);
        }else{
            LOGGER.info("Failed to delete the author");
        }
    }
}
