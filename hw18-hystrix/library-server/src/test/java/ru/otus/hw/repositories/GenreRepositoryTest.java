package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Genre repository test (JPA)")
class GenreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRepository genreRepository;

    private List<Genre> dbGenres;

    @BeforeEach
    void setUp() {
        dbGenres = getDbGenres();
    }

    @Test
    @DisplayName("Get all genres")
    void findAll() {
        var actualGenres = genreRepository.findAll();
        var expectedGenres = dbGenres;
        assertThat(actualGenres)
                .usingRecursiveComparison()
                .isEqualTo(expectedGenres);
    }

    @DisplayName("Get genre by id")
    @ParameterizedTest
    @MethodSource("getDbGenres")
    void shouldGenreById(Genre expectedGenre) {
        var actualAuthor = genreRepository.findById(expectedGenre.getId());
        assertThat(actualAuthor)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedGenre);
    }

    private static List<Genre> getDbGenres() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Genre(id.longValue(), "Genre_" + id))
                .toList();
    }
}