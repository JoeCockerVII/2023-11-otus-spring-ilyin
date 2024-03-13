package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.dto.CommentDto;
import ru.otus.hw.models.mappers.CommentMapper;
import ru.otus.hw.repositories.CommentRepository;
import ru.otus.hw.services.CommentService;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper mapper;

    @Override
    public Mono<CommentDto> findById(String id) {
        return commentRepository.findById(id)
                .switchIfEmpty(Mono.error(NotFoundException::new))
                .map(mapper::toDto);
    }

    @Override
    public Flux<CommentDto> findByBookId(String bookId) {
        return commentRepository.findCommentsByBookId(bookId).map(mapper::toDto);
    }

}
