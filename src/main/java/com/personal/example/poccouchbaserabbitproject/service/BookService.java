package com.personal.example.poccouchbaserabbitproject.service;

import com.personal.example.poccouchbaserabbitproject.dto.in.BookInDTO;
import com.personal.example.poccouchbaserabbitproject.model.Book;

import java.util.List;

public interface BookService {

    List<Book> listAll();

    Book getById(String id);

    Book saveOrUpdate(BookInDTO book);

    void delete(String id);

    void sendBookMessage(String id);
}
