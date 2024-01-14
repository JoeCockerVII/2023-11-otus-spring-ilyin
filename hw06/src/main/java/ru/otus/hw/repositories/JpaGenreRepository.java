package ru.otus.hw.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Genre;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaGenreRepository implements GenreRepository {
    @Override
    public List<Genre> findAll() {
        return null; //todo
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.empty();//todo
    }
}
