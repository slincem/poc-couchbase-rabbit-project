package com.personal.example.poccouchbaserabbitproject.mapper;

import com.personal.example.poccouchbaserabbitproject.dto.in.BookInDTO;
import com.personal.example.poccouchbaserabbitproject.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookInDTOToBook(BookInDTO bookInDTO);
}
