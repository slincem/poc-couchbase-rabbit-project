package com.personal.example.poccouchbaserabbitproject.repository;

import com.personal.example.poccouchbaserabbitproject.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {
}
