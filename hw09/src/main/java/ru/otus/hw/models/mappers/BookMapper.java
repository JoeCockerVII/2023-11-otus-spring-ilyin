package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.models.Book;


@Mapper
public interface BookMapper {

    BookUpdateDto toUpdateDto(Book book);

    BookDto toDto(Book book);

    Book toModel(BookDto dto);


}