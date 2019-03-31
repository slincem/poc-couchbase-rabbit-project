package com.personal.example.poccouchbaserabbitproject.configuration;

import com.personal.example.poccouchbaserabbitproject.model.Book;
import com.personal.example.poccouchbaserabbitproject.repository.BookRepository;
import com.personal.example.poccouchbaserabbitproject.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class BookMessageListener {

    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    public BookMessageListener(BookRepository bookRepository, CustomerRepository customerRepository) {
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public void receiveMessage(Map<String, String>  message) {
        try {
            Thread.sleep(10000);
            log.info("Message received: <" + message + ">");
            String messageId = String.valueOf(message.get("id"));
            Book book = bookRepository.findById(messageId).orElse(null);
            String customerEmail = customerRepository.findById(book.getCustomerWhoBought()).orElseThrow(null).getEmail();
            log.info("Enviando email a... "+ customerEmail);
            book.setCustomerWasNotified(true);
            bookRepository.save(book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
