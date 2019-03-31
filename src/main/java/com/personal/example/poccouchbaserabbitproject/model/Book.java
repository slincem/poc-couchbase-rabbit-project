package com.personal.example.poccouchbaserabbitproject.model;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private String name;

    @Field
    private String author;

    @Field
    private Double price;

    @Field
    private String description;

    @Field
    private Boolean customerWasNotified;

    @Field
    private String customerWhoBought;

    @Override
    public String toString() {
        return "Book{}";
    }
}
