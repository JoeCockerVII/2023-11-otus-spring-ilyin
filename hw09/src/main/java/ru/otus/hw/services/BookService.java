package ru.otus.hw.services;

import ru.otus.hw.models.Book;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookUpdateDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(long id);

    List<Book> findAll();

    Book create(BookCreateDto dto);

    Book update(BookUpdateDto dto);

    void deleteById(long id);
}
