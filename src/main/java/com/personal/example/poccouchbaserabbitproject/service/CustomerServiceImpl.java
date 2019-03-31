package com.personal.example.poccouchbaserabbitproject.service;

import com.personal.example.poccouchbaserabbitproject.configuration.BookRabbitConfiguration;
import com.personal.example.poccouchbaserabbitproject.dto.in.CustomerInDTO;
import com.personal.example.poccouchbaserabbitproject.dto.out.CustomerOutDTO;
import com.personal.example.poccouchbaserabbitproject.mapper.CustomerMapper;
import com.personal.example.poccouchbaserabbitproject.model.Book;
import com.personal.example.poccouchbaserabbitproject.model.Customer;
import com.personal.example.poccouchbaserabbitproject.repository.BookRepository;
import com.personal.example.poccouchbaserabbitproject.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final BookRepository bookRepository;
    private final RabbitTemplate rabbitTemplate;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper, BookRepository bookRepository, RabbitTemplate rabbitTemplate) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.bookRepository = bookRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public List<Customer> listAll() {
        List<Customer> customersInBDCopy = new ArrayList<>();
        customerRepository.findAll().forEach(customersInBDCopy::add);
        return customersInBDCopy;
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer saveOrUpdate(CustomerInDTO customerInDTO) {
        Customer customer = customerMapper.customerInDTOToCustomer(customerInDTO);
        return customerRepository.save(customer);
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerOutDTO buyBook(String customerId, String bookId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(null);
        Book book = bookRepository.findById(bookId).orElseThrow(null);

        customer.addBook(book);
        book.setCustomerWhoBought(customer.getId());

        bookRepository.save(book);
        customerRepository.save(customer);

        sendBookMessage(bookId);
        return customerMapper.customerToCustomerOutDTO(customer);
    }

    private void sendBookMessage(String bookId) {
        Map<String, String> mapBookMessage = new HashMap<>();
        mapBookMessage.put("id", bookId);
        log.info("Sending the index request through queue message");
        rabbitTemplate.convertAndSend(BookRabbitConfiguration.QUEUE_NAME, mapBookMessage);
    }
}
