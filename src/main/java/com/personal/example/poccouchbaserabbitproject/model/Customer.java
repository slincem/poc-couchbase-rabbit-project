package com.personal.example.poccouchbaserabbitproject.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
@EqualsAndHashCode
@Document
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private String name;

    @Field
    private String email;

    @Field
    List<Book> books;

    public Customer() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books.parallelStream().collect(Collectors.toList());
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void deleteBook(Book book) {
        books.remove(book);
    }

    public void deleteBook(int index) {
        books.remove(index);
    }

    @Override
    public String toString() {
        return "Customer{}";
    }
}
