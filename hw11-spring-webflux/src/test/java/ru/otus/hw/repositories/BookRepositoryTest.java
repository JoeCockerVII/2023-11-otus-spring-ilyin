package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    private final Author author = new Author("4", "Author_4");

    private final Genre genre = new Genre("4", "Genre_4");

    @Test
    @DisplayName("Should set id on save")
    void shouldSetIdOnSave() {
        Mono<Book> bookMono = repository.save(new Book("Book_4",author, genre));

        StepVerifier
                .create(bookMono)
                .assertNext(book -> assertNotNull(book.getId()))
                .expectComplete()
                .verify();
    }

    @Test
    @DisplayName("Should return correct books quantity")
    void shouldReturnCorrectBooksQuantity() {
        StepVerifier
                .create(repository.findAll())
                .expectNextCount(3)
                .verifyComplete();
    }

}
