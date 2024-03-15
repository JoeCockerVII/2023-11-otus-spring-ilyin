package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.dto.AuthorDto;

@Mapper
public interface AuthorMapper {

    AuthorDto toDto(Author author);

    Author toModel(AuthorDto dto);

}