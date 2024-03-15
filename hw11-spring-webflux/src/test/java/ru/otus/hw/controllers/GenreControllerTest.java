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
import ru.otus.hw.models.dto.GenreDto;
import ru.otus.hw.services.GenreService;

import static org.mockito.Mockito.when;

@Import(GenreService.class)
@ContextConfiguration(classes = TestConfiguration.class)
@WebFluxTest(controllers = GenreController.class)
public class GenreControllerTest {

    @MockBean
    GenreService genreService;

    @MockBean
    GenreController genreController;

    @InjectMocks
    private TestHelper testHelper;

    @Autowired
    private WebTestClient webClient;


    @Test
    @DisplayName("Should return Genre list")
    void returnAuthorList() {
        var genres = testHelper.getDbGenresDto();

        Flux<GenreDto> genreFlux = Flux.fromIterable(genres);
        when(genreService.findAll()).thenReturn(genreFlux);
        webClient.get()
                .uri("/genres")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(GenreDto.class);
    }


}