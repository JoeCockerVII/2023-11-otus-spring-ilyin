package ru.otus.hw.services;

import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.BookUpdateDto;

import java.util.List;

public interface BookService {

    List<BookDto> findAll();

    BookDto create(BookCreateDto dto);

    BookDto update(long id, BookUpdateDto bookUpdateDto);

    void deleteById(long id);
}
