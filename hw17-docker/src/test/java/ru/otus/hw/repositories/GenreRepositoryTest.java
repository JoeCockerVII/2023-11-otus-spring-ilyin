package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.hw.TestHelper;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Genre repository test (JPA)")
class GenreRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRepository genreRepository;

    @InjectMocks
    private TestHelper testHelper;

    private List<Genre> dbGenres;

    @BeforeEach
    void setUp() {
        dbGenres = testHelper.getDbGenresDto()
                .stream()
                .map(testHelper.getGenreMapper()::toModel)
                .collect(Collectors.toList());
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
    @Test
    void shouldGenreById() {
        var expectedGenre = dbGenres.get(0);
        var actualAuthor = genreRepository.findById(expectedGenre.getId());
        assertThat(actualAuthor)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedGenre);
    }

}