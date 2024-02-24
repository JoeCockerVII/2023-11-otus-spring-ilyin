package ru.otus.hw.models.mappers;

import org.mapstruct.Mapper;
import ru.otus.hw.models.Genre;
import ru.otus.hw.models.dto.GenreDto;

@Mapper
public interface GenreMapper {

    GenreDto toDto(Genre genre);

    Genre toModel(GenreDto dto);

}