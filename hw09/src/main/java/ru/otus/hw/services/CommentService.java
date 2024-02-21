package ru.otus.hw.services;

import ru.otus.hw.models.Comment;
import ru.otus.hw.models.dto.CommentCreateDto;
import ru.otus.hw.models.dto.CommentDto;
import ru.otus.hw.models.dto.CommentUpdateDto;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> findById (long id);

    List<CommentDto> findByBookId (long bookId);

    CommentDto create(CommentCreateDto dto);

    CommentDto update(CommentUpdateDto dto);

    void deleteById(long id);
}
