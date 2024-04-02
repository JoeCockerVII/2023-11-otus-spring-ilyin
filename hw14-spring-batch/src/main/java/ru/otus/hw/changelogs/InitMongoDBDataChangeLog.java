package ru.otus.hw.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.mongodb.client.MongoDatabase;
import ru.otus.hw.models.mongo.AuthorMongo;
import ru.otus.hw.models.mongo.BookMongo;
import ru.otus.hw.models.mongo.CommentMongo;
import ru.otus.hw.models.mongo.GenreMongo;

import java.util.ArrayList;
import java.util.List;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

    private List<AuthorMongo> authors = new ArrayList<>();

    private List<GenreMongo> genres = new ArrayList<>();

    private List<BookMongo> books = new ArrayList<>();

    @ChangeSet(order = "000", id = "dropDB", author = "VII", runAlways = true)
    public void dropDB(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "001", id = "initAuthors", author = "VII", runAlways = true)
    public void initAuthors(MongockTemplate template) {
        authors.add(template.save(new AuthorMongo("Author_1")));
        authors.add(template.save(new AuthorMongo("Author_2")));
        authors.add(template.save(new AuthorMongo("Author_3")));
    }

    @ChangeSet(order = "002", id = "initGenres", author = "VII", runAlways = true)
    public void initGenres(MongockTemplate template) {
        genres.add(template.save(new GenreMongo("Genre_1")));
        genres.add(template.save(new GenreMongo("Genre_2")));
        genres.add(template.save(new GenreMongo("Genre_3")));
    }

    @ChangeSet(order = "003", id = "initBooks", author = "VII", runAlways = true)
    public void initBooks(MongockTemplate template) {
        books.add(template.save(new BookMongo("Book_1", authors.get(0), genres.get(0))));
        books.add(template.save(new BookMongo("Book_2", authors.get(1), genres.get(1))));
        books.add(template.save(new BookMongo("Book_3", authors.get(2), genres.get(2))));
    }

    @ChangeSet(order = "004", id = "initComments", author = "VII", runAlways = true)
    public void initComments(MongockTemplate template) {
        template.save(new CommentMongo("Comment_1", books.get(0)));
        template.save(new CommentMongo("Comment_2", books.get(1)));
        template.save(new CommentMongo("Comment_3", books.get(2)));
    }
}
