package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.models.mappers.BookMapper;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.GenreRepository;
import ru.otus.hw.services.BookService;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    private final BookMapper mapper;

    @Override
    public Mono<BookDto> findById(String id) {
        return bookRepository.findById(id).map(mapper::toDto);
    }

    @Override
    public Flux<BookDto> findAll() {
        return bookRepository.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<BookDto> create(BookCreateDto dto) {
        //todo
        return null;
    }

    @Override
    public Mono<BookDto> update(BookUpdateDto dto) {
        //todo
        return null;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }
}
