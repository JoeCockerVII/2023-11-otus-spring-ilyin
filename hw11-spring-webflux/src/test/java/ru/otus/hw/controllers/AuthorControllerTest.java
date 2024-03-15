package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import ru.otus.hw.TestHelper;
import ru.otus.hw.configuration.TestConfiguration;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.services.AuthorService;
import static org.mockito.Mockito.when;

@Import(AuthorService.class)
@ContextConfiguration(classes = TestConfiguration.class)
@WebFluxTest(controllers = AuthorController.class)
class AuthorControllerTest {

    @MockBean
    AuthorService authorService;

    @MockBean
    AuthorController authorController;

    @InjectMocks
    private TestHelper testHelper;

    @Autowired
    private WebTestClient webClient;


    @Test
    @DisplayName("Should return Author list")
    void returnAuthorList() {
        var authors = testHelper.getDbAuthorsDto();

        Flux<AuthorDto> authorFlux = Flux.fromIterable(authors);
        when(authorService.findAll()).thenReturn(authorFlux);
        webClient.get()
                .uri("/authors")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AuthorDto.class);
    }

}