package ru.otus.hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.CommentDto;
import ru.otus.hw.models.h2.Author;
import ru.otus.hw.models.mappers.BookMapper;
import ru.otus.hw.models.mappers.CommentMapper;
import ru.otus.hw.models.h2.Book;
import ru.otus.hw.models.h2.Comment;
import ru.otus.hw.models.h2.Genre;
import ru.otus.hw.models.mongo.AuthorMongo;
import ru.otus.hw.models.mongo.BookMongo;
import ru.otus.hw.models.mongo.CommentMongo;
import ru.otus.hw.models.mongo.GenreMongo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConversionService {

    private final BookMapper bookMapper;

    private final CommentMapper commentMapper;

    private final Map<String, Author> authors = new HashMap<>();

    private final Map<String, Genre> genres = new HashMap<>();

    private final Map<String, Book> books = new HashMap<>();

    public Author convert(AuthorMongo authorMongo) {

        Author author = new Author(UUID.randomUUID().toString(), authorMongo.getFullName());
        authors.put(authorMongo.getId(), author);

        return author;
    }

    public Genre convert(GenreMongo genreMongo) {

        Genre genre = new Genre(UUID.randomUUID().toString(), genreMongo.getName());
        genres.put(genreMongo.getId(), genre);

        return genre;
    }

    public BookDto convert(BookMongo bookMongo) {

        Book book = new Book(UUID.randomUUID().toString(),
                bookMongo.getTitle(),
                authors.get(bookMongo.getAuthorMongo().getId()),
                genres.get(bookMongo.getGenreMongo().getId()));

        books.put(bookMongo.getId(), book);

        return bookMapper.toDto(book);
    }

    public CommentDto convert(CommentMongo commentMongo) {

        Comment comment = new Comment(UUID.randomUUID().toString(), commentMongo.getText(),
                books.get(commentMongo.getBookMongo().getId()));

        return commentMapper.toDto(comment);
    }
}