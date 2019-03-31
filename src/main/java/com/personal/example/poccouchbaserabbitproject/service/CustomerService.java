package com.personal.example.poccouchbaserabbitproject.service;

import com.personal.example.poccouchbaserabbitproject.dto.in.CustomerInDTO;
import com.personal.example.poccouchbaserabbitproject.dto.out.CustomerOutDTO;
import com.personal.example.poccouchbaserabbitproject.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> listAll();

    Customer getById(String id);

    Customer saveOrUpdate(CustomerInDTO customer);

    void delete(String id);

    CustomerOutDTO buyBook(String customerId, String bookId);

}
