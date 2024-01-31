package changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw.models.Author;
import ru.otus.hw.models.Book;
import ru.otus.hw.models.Comment;
import ru.otus.hw.models.Genre;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.repositories.BookRepository;
import ru.otus.hw.repositories.CommentRepository;
import ru.otus.hw.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private List<Author> authors = new ArrayList<>();

    private List<Genre> genres = new ArrayList<>();

    private List<Book> books = new ArrayList<>();

    @ChangeSet(order = "000", id = "dropDB", author = "VII", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "VII", runAlways = true)
    public void initAuthors(AuthorRepository authorRepository) {
        authors.add(authorRepository.save(new Author("Author_1")));
        authors.add(authorRepository.save(new Author("Author_2")));
        authors.add(authorRepository.save(new Author("Author_3")));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "VII", runAlways = true)
    public void initGenres(GenreRepository genreRepository) {
        genres.add(genreRepository.save(new Genre("Genre_1")));
        genres.add(genreRepository.save(new Genre("Genre_2")));
        genres.add(genreRepository.save(new Genre("Genre_3")));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "VII", runAlways = true)
    public void initBooks(BookRepository bookRepository) {
        books.add(bookRepository.save(new Book("Book_1", authors.get(0), genres.get(0))));
        books.add(bookRepository.save(new Book("Book_2", authors.get(1), genres.get(1))));
        books.add(bookRepository.save(new Book("Book_3", authors.get(2), genres.get(2))));
    }

    @ChangeSet(order = "004", id = "initComments", author = "yan", runAlways = true)
    public void initComments(CommentRepository commentRepository) {
        commentRepository.save(new Comment("Comment_1", books.get(0)));
        commentRepository.save(new Comment("Comment_2", books.get(1)));
        commentRepository.save(new Comment("Comment_3", books.get(2)));
    }
}
