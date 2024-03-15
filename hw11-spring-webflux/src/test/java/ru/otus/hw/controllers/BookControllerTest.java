package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.TestHelper;
import ru.otus.hw.configuration.TestConfiguration;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.services.BookService;

import static org.mockito.Mockito.*;


@Import(BookService.class)
@ContextConfiguration(classes = TestConfiguration.class)
@WebFluxTest(controllers = BookController.class)
public class BookControllerTest {

    @MockBean
    BookService bookService;

    @MockBean
    BookController bookController;

    @InjectMocks
    private TestHelper testHelper;

    @Autowired
    private WebTestClient webClient;


    @Test
    @DisplayName("Should get book list")
    void shouldGetBookList() {
        var books = testHelper.getDbBooks();

        Flux<BookDto> bookFlux = Flux.fromIterable(books);
        when(bookService.findAll()).thenReturn(bookFlux);
        webClient.get()
                .uri("/books")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class);
    }

    @Test
    @DisplayName("Should create book")
    void shouldCreateBook() {

        var book = new BookCreateDto("Title",  "1", "1");

        webClient.post().uri("/books")
                .body(Mono.just(book), BookCreateDto.class)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    @DisplayName("Should update book")
    void shouldUpdateBook() {

        var book = new BookUpdateDto("1","Title_New",  "1", "1");

        webClient.put().uri("/books/{id}","1")
                .body(Mono.just(book), BookUpdateDto.class)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @DisplayName("Should delete book")
    void shouldDeleteBook() {
		Mono<Void> voidReturn  = Mono.empty();
        Mockito.when(bookService.deleteById("1")).thenReturn(voidReturn);

        webClient.delete().uri("/books/{id}", 1)
	        .exchange()
	        .expectStatus().isNoContent();
    }

}