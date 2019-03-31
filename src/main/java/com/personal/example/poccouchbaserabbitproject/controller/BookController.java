package com.personal.example.poccouchbaserabbitproject.controller;

import com.personal.example.poccouchbaserabbitproject.dto.in.BookInDTO;
import com.personal.example.poccouchbaserabbitproject.model.Book;
import com.personal.example.poccouchbaserabbitproject.service.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book createOrUpdateBook(@RequestBody BookInDTO book) {
        return bookService.saveOrUpdate(book);
    }

    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable String bookId){
        return bookService.getById(bookId);
    }

}
