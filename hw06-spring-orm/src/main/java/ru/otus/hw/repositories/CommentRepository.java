package ru.otus.hw.repositories;

import ru.otus.hw.models.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> findById(long id);

    List<Comment> findAllCommentByBookId(long bookId);

    void delete(long id);

    Comment saveOrUpdate(Comment comment);

}