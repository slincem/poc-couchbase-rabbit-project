package com.personal.example.poccouchbaserabbitproject.mapper;

import com.personal.example.poccouchbaserabbitproject.dto.in.CustomerInDTO;
import com.personal.example.poccouchbaserabbitproject.dto.out.CustomerOutDTO;
import com.personal.example.poccouchbaserabbitproject.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer customerInDTOToCustomer(CustomerInDTO customerInDTO);
    CustomerOutDTO customerToCustomerOutDTO(Customer customer);
}
