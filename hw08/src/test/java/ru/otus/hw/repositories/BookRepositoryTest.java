package ru.otus.hw.repositories;


import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("BookRepositoryTest ")
@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"ru.otus.hw.repositories"})
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private final Genre genre = new Genre("1", "Genre_1");

    private final Author author = new Author("1", "Author_1");


    @DisplayName("должен загружать книгу по id")
    @Test
    void shouldReturnExpectedBookById() {
        var expectedBook = new Book("2", "Book_2", author, genre);
        bookRepository.save(expectedBook);

        var actualBook = bookRepository.findById(expectedBook.getId());

        assertThat(actualBook)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedBook);
    }

    @DisplayName("должен сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        var expectedBook = new Book("BookTitle_10500", author, genre);

        var returnedBook = bookRepository.save(expectedBook);

        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() != null)
                .usingRecursiveComparison().ignoringExpectedNullFields()
                .isEqualTo(expectedBook);

        assertThat(bookRepository.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(returnedBook);
    }

    @DisplayName("должен сохранять измененную книгу")
    @Test
    void shouldSaveUpdatedBook() {
        
        var expectedBook = new Book("1", "BookTitle_10500", author, genre);
        bookRepository.save(expectedBook);


        assertThat(bookRepository.findById(expectedBook.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectedBook);

        expectedBook.setTitle("BookTitle_10501");

        var returnedBook = bookRepository.save(expectedBook);

        assertThat(returnedBook).isNotNull()
                .matches(book -> book.getId() != null)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedBook);

        assertThat(bookRepository.findById(returnedBook.getId()))
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .ignoringExpectedNullFields()
                .isEqualTo(returnedBook);
    }
}