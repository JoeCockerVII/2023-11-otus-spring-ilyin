package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.Book;
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
        return Mono.zip(
                        authorRepository.findById(dto.getAuthorId())
                                .switchIfEmpty(Mono.error(NotFoundException::new)),
                        genreRepository.findById(dto.getGenreId())
                                .switchIfEmpty(Mono.error(NotFoundException::new)),
                        (author, genre) -> new Book(dto.getTitle(), author, genre))
                .flatMap(bookRepository::save)
                .map(mapper::toDto);
    }

    @Override
    public Mono<BookDto> update(BookUpdateDto dto) {
        return Mono.zip(
                        authorRepository.findById(dto.getAuthorId())
                                .switchIfEmpty(Mono.error(NotFoundException::new)),
                        genreRepository.findById(dto.getGenreId())
                                .switchIfEmpty(Mono.error(NotFoundException::new)))
                .flatMap(tuple -> bookRepository.findById(dto.getId())
                        .switchIfEmpty(Mono.error(NotFoundException::new))
                        .flatMap(book -> {
                            book.setTitle(dto.getTitle());
                            book.setAuthor(tuple.getT1());
                            book.setGenre(tuple.getT2());
                            return bookRepository.save(book).map(mapper::toDto);
                        })
                );
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return bookRepository.deleteById(id);
    }
}



//.flatMap(book -> {
//        book.setTitle(dto.getTitle());
//        book.setAuthor(author);
//        book.setGenre(genre);
//        return bookRepository.save(book).map(mapper::toDto);
//        })

//                            return bookRepository.findById(dto.getId())
//                                    .switchIfEmpty(Mono.error(NotFoundException::new))
//                                    .flatMap(book -> {
//                                        book.setTitle(dto.getTitle());
//                                        book.setAuthor(author);
//                                        book.setGenre(genre);
//                                        return bookRepository.save(book).map(mapper::toDto).hide();
//                                    })
//                            ;
//                        }
//                ).block();
////                .flatMap(bookRepository::save)
////                .map(mapper::toDto);