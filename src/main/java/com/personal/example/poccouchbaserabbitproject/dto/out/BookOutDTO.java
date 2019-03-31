package com.personal.example.poccouchbaserabbitproject.dto.out;

import com.personal.example.poccouchbaserabbitproject.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookOutDTO {

    private String id;
    private String name;
    private String author;
    private Double price;
    private String description;
    private Boolean customerWasNotified;
    private Customer customerWhoBought;
}
