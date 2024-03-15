MERGE INTO authors KEY (author_id, full_name) VALUES (1, 'Author_1'), (2, 'Author_2'), (3, 'Author_3');
ALTER TABLE AUTHORS ALTER COLUMN author_id RESTART WITH 4;


MERGE INTO genres KEY (genre_id, name) VALUES (1, 'Genre_1'), (2, 'Genre_2'), (3, 'Genre_3');
ALTER TABLE GENRES ALTER COLUMN genre_id RESTART WITH 4;


MERGE INTO books KEY (book_id, title, genre_id, author_id) VALUES (1, 'BookTitle_1', 1, 1), (2, 'BookTitle_2', 2, 2), (3, 'BookTitle_3', 3, 3);
ALTER TABLE BOOKS ALTER COLUMN book_id RESTART WITH 4;


MERGE INTO comments KEY (comment_id, text, book_id) VALUES (1, 'Super', 1), (2, 'Awesome', 1);
ALTER TABLE COMMENTS ALTER COLUMN comment_id RESTART WITH 4;

MERGE INTO  users KEY (user_id, username, password)
    VALUES (1, 'user', '$2a$12$SIs.hJKuWxsVlsPNVN53/eKMEb4Ks97ikMe64hCJrr8GKPQt2hbIK'),
           (2, 'admin', '$2a$12$we6cdYeikeAH.cpaOwzum.xtm1f2jHLHmaLrtdEw5JpnoJ/8x4Iz2');
ALTER TABLE USERS ALTER COLUMN user_id RESTART WITH  3;
