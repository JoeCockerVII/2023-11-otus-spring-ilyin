package ru.otus.hw.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Comment repository test (JPA)")
@DataJpaTest
@Import({JpaCommentRepository.class})
class JpaCommentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JpaCommentRepository commentRepository;

    @DisplayName("Should get comment by id")
    @ParameterizedTest
    @MethodSource("getDbComments")
    void shouldGetCommentById(Comment expected) {
        System.out.println(expected);
        var actualComment = commentRepository.findById(expected.getId());
        assertThat(actualComment).isPresent().get().isEqualTo(expected);
    }

    @DisplayName("Should get list comment by bookId")
    @Test
    void shouldGetCommentsByBooksId() {
        var expectedComments = getDbComments();
        var actualComments = commentRepository.findAllCommentByBookId(1);
        assertThat(actualComments).containsExactlyElementsOf(expectedComments);
    }

    @Test
    @DisplayName("Should delete comment by id")
    void shouldDeleteComment() {
        assertThat(commentRepository.findById(1L)).isPresent();
        commentRepository.delete(1L);
        assertThat(commentRepository.findById(1L)).isEmpty();
    }

    @Test
    void saveOrUpdate() {
        var expectedComment = new Comment(1L, "SomeText", getDbBooks().get(0));

        assertThat(commentRepository.findById(expectedComment.getId()))
                .isPresent()
                .get()
                .isNotEqualTo(expectedComment);

        var returnedComment = commentRepository.saveOrUpdate(expectedComment);

        assertThat(returnedComment).isNotNull()
                .matches(comment -> comment.getId() > 0)
                .usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(expectedComment);

        assertThat(commentRepository.findById(expectedComment.getId()))
                .isPresent()
                .get()
                .isEqualTo(returnedComment);
    }

    private static List<Comment> getDbComments(List<Book> dbBooks) {
        return List.of(new Comment(1, "Super", dbBooks.get(0)),
                new Comment(2, "Awesome", dbBooks.get(0)));
    }

    private static List<Comment> getDbComments() {
        var dbBooks = getDbBooks();
        return getDbComments(dbBooks);
    }

    private static List<Book> getDbBooks() {
        var dbAuthors = getDbAuthors();
        var dbGenres = getDbGenres();
        return getDbBooks(dbAuthors, dbGenres);
    }

    private static List<Author> getDbAuthors() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Author(id, "Author_" + id))
                .toList();
    }

    private static List<Genre> getDbGenres() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Genre(id, "Genre_" + id))
                .toList();
    }

    private static List<Book> getDbBooks(List<Author> dbAuthors, List<Genre> dbGenres) {
        return IntStream.range(1, 4).boxed()
                .map(id -> new Book(id, "BookTitle_" + id, dbAuthors.get(id - 1), dbGenres.get(id - 1)))
                .toList();
    }

}