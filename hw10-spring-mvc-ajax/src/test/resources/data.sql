MERGE INTO authors KEY (author_id, full_name) VALUES (1, 'Author_1'), (2, 'Author_2'), (3, 'Author_3');
ALTER TABLE AUTHORS ALTER COLUMN author_id RESTART WITH 4;


MERGE INTO genres KEY (genre_id, name) VALUES (1, 'Genre_1'), (2, 'Genre_2'), (3, 'Genre_3');
ALTER TABLE GENRES ALTER COLUMN genre_id RESTART WITH 4;


MERGE INTO books KEY (book_id, title, genre_id, author_id) VALUES (1, 'BookTitle_1', 1, 1), (2, 'BookTitle_2', 2, 2), (3, 'BookTitle_3', 3, 3);
ALTER TABLE BOOKS ALTER COLUMN book_id RESTART WITH 4;

MERGE INTO comments KEY (comment_id, ) VALUES (1, , 1), (2, );
ALTER TABLE COMMENTS ALTER COLUMN comment_id RESTART WITH 4;

-- insert into authors(full_name)
-- values ('Author_1'), ('Author_2'), ('Author_3');
--
-- insert into genres(name)
-- values ('Genre_1'), ('Genre_2'), ('Genre_3');
--
-- insert into books(title, author_id, genre_id)
-- values ('BookTitle_1', 1, 1), ('BookTitle_2', 2, 2), ('BookTitle_3', 3, 3);
--
-- insert into comments(text, book_id)
-- values ('Super', 1), ('Awesome', 1);
