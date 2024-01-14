package ru.otus.hw.repositories;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Author;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaAuthorRepository implements AuthorRepository {
    @Override
    public List<Author> findAll() {
        return null;//todo
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.empty();//todo
    }
}
