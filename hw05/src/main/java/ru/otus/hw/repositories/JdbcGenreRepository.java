package ru.otus.hw.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcGenreRepository implements GenreRepository {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public List<Genre> findAll() {
        return jdbc.query("Select genre_id, name from genres",new GenreRowMapper());
    }

    @Override
    public Optional<Genre> findById(long id) {
        var result = jdbc.query("select genre_id, name from genres where genre_id = :id",
                Collections.singletonMap("id", id), new GenreRowMapper());
        return result.isEmpty() ? Optional.empty() : result.stream().findFirst();

    }

    private static class GenreRowMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int i) throws SQLException {
            return new Genre(rs.getLong("genre_id"),rs.getString("name"));
        }
    }
}
