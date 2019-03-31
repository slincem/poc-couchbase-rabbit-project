package com.personal.example.poccouchbaserabbitproject.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookInDTO {

    private String name;
    private String author;
    private Double price;
    private String description;
}
