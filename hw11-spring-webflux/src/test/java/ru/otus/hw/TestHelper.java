package ru.otus.hw;

import org.mapstruct.factory.Mappers;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.GenreDto;
import ru.otus.hw.models.mappers.AuthorMapper;
import ru.otus.hw.models.mappers.GenreMapper;

import java.util.List;
import java.util.stream.IntStream;

public class TestHelper {

    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    private final GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

    public List<AuthorDto> getDbAuthorsDto() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new AuthorDto(id.toString(), "Author_" + id))
                .toList();
    }

    public List<GenreDto> getDbGenresDto() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new GenreDto(id.toString(), "Genre_" + id))
                .toList();
    }

    public List<BookDto> getDbBooks(List<AuthorDto> dbAuthors, List<GenreDto> dbGenres) {
        return IntStream.range(1, 4).boxed()
                .map(id -> new BookDto(id.toString(), "BookTitle_" + id, dbAuthors.get(id - 1), dbGenres.get(id - 1)))
                .toList();
    }

    public List<BookDto> getDbBooks() {
        return getDbBooks(getDbAuthorsDto(), getDbGenresDto());
    }
}
