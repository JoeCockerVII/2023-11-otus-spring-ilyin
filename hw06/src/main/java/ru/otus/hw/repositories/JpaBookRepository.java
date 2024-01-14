package ru.otus.hw.repositories;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import ru.otus.hw.models.Book;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaBookRepository implements BookRepository {
    @Override
    public Optional<Book> findById(long id) {
        return Optional.empty();//todo
    }

    @Override
    public List<Book> findAll() {
        return null;//todo
    }

    @Override
    public Book save(Book book) {
        return null;//todo
    }

    @Override
    public void deleteById(long id) {
        //todo
    }
}