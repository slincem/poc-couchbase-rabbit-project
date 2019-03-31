package com.personal.example.poccouchbaserabbitproject.dto.out;

import com.personal.example.poccouchbaserabbitproject.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerOutDTO {

    private String id;
    private String name;
    private String email;
    List<Book> books;
}
