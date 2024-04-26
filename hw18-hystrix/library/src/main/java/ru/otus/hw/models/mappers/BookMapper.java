package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Genre;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.Book;


@Mapper
public interface BookMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "genre", source = "genre")
    BookDto toDto(Book book);

    @Mapping(target = "id", ignore = true)
    Book toModel(BookCreateDto dto, Author author, Genre genre);


}