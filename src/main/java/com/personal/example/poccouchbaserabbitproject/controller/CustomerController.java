package com.personal.example.poccouchbaserabbitproject.controller;

import com.personal.example.poccouchbaserabbitproject.dto.in.CustomerInDTO;
import com.personal.example.poccouchbaserabbitproject.dto.out.CustomerOutDTO;
import com.personal.example.poccouchbaserabbitproject.model.Customer;
import com.personal.example.poccouchbaserabbitproject.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createOrUpdateCustomer(@RequestBody CustomerInDTO customer){
        return customerService.saveOrUpdate(customer);
    }

    @GetMapping("/{customerId}")
    public Customer findCustomer(@PathVariable String customerId) {
        return customerService.getById(customerId);
    }

    @PostMapping("{customerId}/book/{bookId}")
    public CustomerOutDTO buyBook(@PathVariable String customerId, @PathVariable String bookId) {
        //TODO Comprar libro y devolver comprador con el mismo. En el servicio del comprador, enviar mensaje a la cola.
        return customerService.buyBook(customerId, bookId);
    }
}
