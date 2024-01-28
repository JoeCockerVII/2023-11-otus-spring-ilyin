package ru.otus.hw.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw.models.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, Long> {

    List<Comment> findCommentsByBookId(long bookId);

}