package ru.otus.hw.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcAuthorRepository implements AuthorRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Author> findAll() {
        return jdbc.query("Select author_id, full_name from authors",new AuthorRowMapper());
    }

    @Override
    public Optional<Author> findById(long id) {
        var result = jdbc.query("select author_id, full_name from authors where author_id = :id",
                Collections.singletonMap("id", id), new AuthorRowMapper());

        return result.isEmpty() ? Optional.empty() : result.stream().findFirst();
    }

    private static class AuthorRowMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int i) throws SQLException {
            return new Author(rs.getLong("author_id"),rs.getString("full_name"));
        }
    }
}
