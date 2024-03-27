package ru.otus.hw.services;

import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.BookUpdateDto;

import java.util.List;

public interface BookService {
    BookDto findById(long id);

    List<BookDto> findAll();

    BookDto create(BookCreateDto dto);

    BookDto update(BookUpdateDto dto);

    void deleteById(long id);
}
