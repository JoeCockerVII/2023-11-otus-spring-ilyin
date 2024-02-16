package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.EntityNotFoundException;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.GenreRepository;
import ru.otus.hw.services.BookService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    private final BookRepository bookRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional
    @Override
    public Book create(BookCreateDto dto) {

        var author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author id %d not found".formatted(dto.getAuthorId())));
        var genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() -> new EntityNotFoundException("Genre id %d not found".formatted(dto.getGenreId())));
        var book = new Book(0L, dto.getTitle(), author, genre);

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(BookUpdateDto dto) {

        var book = bookRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Book id %d not found".formatted(dto.getId())));
        var author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author id %d not found".formatted(dto.getAuthorId())));
        var genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() -> new EntityNotFoundException("Genre id %d not found".formatted(dto.getGenreId())));

        book.setTitle(dto.getTitle());
        book.setAuthor(author);
        book.setGenre(genre);

        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }
}