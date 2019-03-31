package com.personal.example.poccouchbaserabbitproject.repository;

import com.personal.example.poccouchbaserabbitproject.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String> {
}
