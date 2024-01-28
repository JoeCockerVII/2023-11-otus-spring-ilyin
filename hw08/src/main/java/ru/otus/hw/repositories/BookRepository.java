package ru.otus.hw.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book,Long> {

    Optional<Book> findById(long id);

    List<Book> findAll();
}