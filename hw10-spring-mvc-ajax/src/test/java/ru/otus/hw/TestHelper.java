package ru.otus.hw;

import org.mapstruct.factory.Mappers;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Genre;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.GenreDto;
import ru.otus.hw.models.mappers.AuthorMapper;
import ru.otus.hw.models.mappers.GenreMapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestHelper {

    private final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    private final GenreMapper genreMapper = Mappers.getMapper(GenreMapper.class);

    public List<AuthorDto> getDbAuthorsDto() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new AuthorDto(id.longValue(), "Author_" + id))
                .toList();
    }

    public List<GenreDto> getDbGenresDto() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new GenreDto(id.longValue(), "Genre_" + id))
                .toList();
    }


    public List<BookDto> getDbBooks(List<Author> dbAuthors, List<Genre> dbGenres) {
        return IntStream.range(1, 4).boxed()
                .map(id -> new BookDto(id.longValue(), "BookTitle_" + id, dbAuthors.get(id - 1), dbGenres.get(id - 1)))
                .toList();
    }

    public List<BookDto> getDbBooks() {
        var dbAuthors = getDbAuthorsDto().stream().map(authorMapper::toModel).collect(Collectors.toList());
        var dbGenres= getDbGenresDto().stream().map(genreMapper::toModel).collect(Collectors.toList());
        return getDbBooks(dbAuthors, dbGenres);
    }
}
