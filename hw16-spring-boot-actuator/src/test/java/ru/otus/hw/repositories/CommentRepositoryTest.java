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
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comment repository test (JPA)")
@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CommentRepository commentRepository;


    @InjectMocks
    private TestHelper testHelper;

    private List<Author> dbAuthors;

    private List<Genre> dbGenres;

    private List<Book> dbBooks;

    @BeforeEach
    void setUp() {
        dbGenres = testHelper.getDbGenresDto()
                .stream()
                .map(testHelper.getGenreMapper()::toModel)
                .collect(Collectors.toList());

        dbAuthors = testHelper.getDbAuthorsDto()
                .stream()
                .map(testHelper.getAuthorMapper()::toModel)
                .collect(Collectors.toList());

        dbBooks = testHelper.getDbBooks(testHelper.getDbAuthorsDto(), testHelper.getDbGenresDto())
                .stream()
                .map(testHelper.getBookMapper()::toModel)
                .collect(Collectors.toList());
    }



    @DisplayName("Should get comment by id")
    @Test
    void shouldGetCommentById() {
        var expected = getDbComments(dbBooks).get(0);
        System.out.println(expected);
        var actualComment = commentRepository.findById(expected.getId());
        assertThat(actualComment)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(expected);
    }

    @DisplayName("Should get list comment by bookId")
    @Test
    void shouldGetCommentsByBooksId() {
        var expectedComments = getDbComments(dbBooks);
        var actualComments = commentRepository.findCommentsByBookId(1L);
        assertThat(expectedComments)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(actualComments);
    }

    @Test
    @DisplayName("Should delete comment by id")
    void shouldDeleteComment() {
        assertThat(commentRepository.findById(1L)).isPresent();
        commentRepository.deleteById(1L);
        assertThat(commentRepository.findById(1L)).isEmpty();
    }

    @Test
    void saveOrUpdate() {
        var expectedComment = new Comment(1L, "SomeText", dbBooks.get(0));

        assertThat(commentRepository.findById(expectedComment.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectedComment);

        var returnedComment = commentRepository.save(expectedComment);

        assertThat(returnedComment).isNotNull()
                .matches(comment -> comment.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedComment);

        assertThat(commentRepository.findById(expectedComment.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedComment);
    }

    private List<Comment> getDbComments(List<Book> dbBooks) {
        return List.of(new Comment(1L, "Super", dbBooks.get(0)),
                new Comment(2L, "Awesome", dbBooks.get(1)));
    }

}