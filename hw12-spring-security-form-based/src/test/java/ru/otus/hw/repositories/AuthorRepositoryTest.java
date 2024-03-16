package ru.otus.hw.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.hw.TestHelper;
import ru.otus.hw.models.Author;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("Author repository test (JPA)")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @InjectMocks
    private TestHelper testHelper;

    private List<Author> dbAuthors;

    @BeforeEach
    void setUp() {
        dbAuthors = testHelper.getDbAuthorsDto()
                .stream()
                .map(testHelper.getAuthorMapper()::toModel)
                .collect(Collectors.toList());
    }

    @DisplayName("Get all books")
    @Test
    void shouldGetAllAuthors() {
        var actualAuthors = authorRepository.findAll();
        var expectedAuthors = dbAuthors;
        assertThat(actualAuthors)
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthors);
    }

    @DisplayName("Book by id")
    @Test
    void shouldGetAuthorById() {
        var expectedAuthor = dbAuthors.get(0);
        var actualAuthor = authorRepository.findById(expectedAuthor.getId());
        assertThat(actualAuthor)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(expectedAuthor);
    }

}