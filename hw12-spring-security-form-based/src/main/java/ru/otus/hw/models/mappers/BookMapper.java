package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.Book;


@Mapper
public interface BookMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "genre", source = "genre")
    BookDto toDto(Book book);

    Book toModel(BookDto dto);


}