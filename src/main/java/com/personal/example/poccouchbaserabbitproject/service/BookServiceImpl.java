package com.personal.example.poccouchbaserabbitproject.service;

import com.personal.example.poccouchbaserabbitproject.configuration.BookRabbitConfiguration;
import com.personal.example.poccouchbaserabbitproject.dto.in.BookInDTO;
import com.personal.example.poccouchbaserabbitproject.mapper.BookMapper;
import com.personal.example.poccouchbaserabbitproject.model.Book;
import com.personal.example.poccouchbaserabbitproject.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class BookServiceImpl implements  BookService {

    private final BookRepository bookRepository;
    private final RabbitTemplate rabbitTemplate;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, RabbitTemplate rabbitTemplate, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<Book> listAll() {
        List<Book> booksInBDCopy = new ArrayList<>();
        bookRepository.findAll().forEach(booksInBDCopy::add);
        return booksInBDCopy;
    }

    @Override
    public Book getById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book saveOrUpdate(BookInDTO bookInDTO) {
        Book book = bookMapper.bookInDTOToBook(bookInDTO);
        return bookRepository.save(book);
    }

    @Override
    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void sendBookMessage(String id) {
        Map<String, String> mapBookMessage = new HashMap<>();
        mapBookMessage.put("id", id);
        log.info("Sending the index request through queue message");
        rabbitTemplate.convertAndSend(BookRabbitConfiguration.QUEUE_NAME, mapBookMessage);
    }
}
