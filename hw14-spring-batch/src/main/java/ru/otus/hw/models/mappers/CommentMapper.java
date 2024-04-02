package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.otus.hw.models.dto.CommentDto;
import ru.otus.hw.models.h2.Comment;

@Mapper
public interface CommentMapper {

    @Mapping(target = "bookId", source = "book.id")
    CommentDto toDto(Comment comment);

    @Mapping(target = "book", ignore = true)
    Comment toModel(CommentDto dto);

}