package ru.otus.hw.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw.exceptions.EntityNotFoundException;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Optional<Book> findById(long id) {
        try {
            return Optional.ofNullable(jdbc.queryForObject("""
                    Select t1.id as id, t1.title as title,
                           t2.id as author_id, t2.full_name as author_name,
                           t3.id as genre_id, t3.name as genre_name
                    from books t1
                    join authors t2 on t1.author_id = t2.id
                    join genres t3 on t1.genre_id = t3.id
                    where t1.id = :id
                    """, Collections.singletonMap("id", id), new BookRowMapper())
            );
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Book> findAll() {
        return jdbc.query("""
                    Select t1.id as id, t1.title as title,
                           t2.id as author_id, t2.full_name as author_name,
                           t3.id as genre_id, t3.name as genre_name
                    from books t1 
                    join authors t2 on t1.author_id = t2.id
                    join genres t3 on t1.genre_id = t3.id;
              """, new BookRowMapper());
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0) {
            return insert(book);
        }
        return update(book);
    }

    @Override
    public void deleteById(long id) {
        jdbc.update("delete from books where id = :id", Map.of("id", id));
    }

    private Book insert(Book book) {
        var keyHolder = new GeneratedKeyHolder();

        var params = Map.of("id", book.getId(),
                "title", book.getTitle(),
                "authorId", book.getAuthor().getId(),
                "genreId", book.getGenre().getId()
        );

        jdbc.update("INSERT INTO books(id, title, genre_id, author_id) VALUES (:id, :title, :genreId, :authorId)",
                new MapSqlParameterSource(params), keyHolder
        );

        //noinspection DataFlowIssue
        book.setId(keyHolder.getKeyAs(Long.class));
        return book;
    }

    private Book update(Book book) {

        Optional.of(
                jdbc.update(
                        "UPDATE Books SET title = :title, author_id = :authorId, genre_id = :genreId WHERE ID = :id",
                    Map.of("id", book.getId(),
                            "title", book.getTitle(),
                            "authorId", book.getAuthor().getId(),
                            "genreId", book.getGenre().getId())))
                .orElseThrow(() -> new EntityNotFoundException("No books have been updated"));
        return book;
    }

    private static class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Book.builder()
                    .id(rs.getLong("id"))
                    .title(rs.getString("title"))
                    .author(new Author(rs.getLong("author_id"),rs.getString("author_name")))
                    .genre(new Genre(rs.getLong("genre_id"), rs.getString("genre_name")))
                    .build();
        }
    }
}