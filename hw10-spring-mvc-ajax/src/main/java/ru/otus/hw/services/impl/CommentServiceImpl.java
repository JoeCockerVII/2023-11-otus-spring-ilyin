package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.Comment;
import ru.otus.hw.models.dto.CommentCreateDto;
import ru.otus.hw.models.dto.CommentDto;
import ru.otus.hw.models.dto.CommentUpdateDto;
import ru.otus.hw.models.mappers.CommentMapper;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;
import ru.otus.hw.services.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final BookRepository bookRepository;

    private final CommentMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> findById(long id) {
        return commentRepository.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CommentDto> findByBookId(long bookId) {
        return commentRepository.findCommentsByBookId(bookId)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentDto create(CommentCreateDto dto) {
        var book = bookRepository.findById(dto.getBookId()).orElseThrow(NotFoundException::new);
        var comment = new Comment(null, dto.getText(), book);
        return mapper.toDto(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public CommentDto update(CommentUpdateDto dto) {
        var comment = commentRepository.findById(dto.getId()).orElseThrow(NotFoundException::new);
        comment.setText(dto.getText());
        return mapper.toDto(commentRepository.save(comment));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

}
