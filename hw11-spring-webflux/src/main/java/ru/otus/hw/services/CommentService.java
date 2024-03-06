package ru.otus.hw.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.models.dto.CommentDto;

public interface CommentService {
    Mono<CommentDto> findById (String id);

    Flux<CommentDto> findByBookId (String bookId);
}
