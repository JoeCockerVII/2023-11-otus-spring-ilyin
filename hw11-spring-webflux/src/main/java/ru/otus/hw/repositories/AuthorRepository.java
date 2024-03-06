package ru.otus.hw.repositories;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;
import ru.otus.hw.models.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {

    @NotNull
    Mono<Author> findById(@NotNull String id);
}