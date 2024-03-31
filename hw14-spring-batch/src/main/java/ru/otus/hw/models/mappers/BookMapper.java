package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.h2.Book;


@Mapper
public interface BookMapper {

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "genreId", source = "genre.id")
    BookDto toDto(Book book);

}