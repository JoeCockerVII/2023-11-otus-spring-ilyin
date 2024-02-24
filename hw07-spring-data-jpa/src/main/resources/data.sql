MERGE INTO authors KEY (author_id, full_name) VALUES (1, 'Author_1'), (2, 'Author_2'), (3, 'Author_3');

MERGE INTO genres KEY (genre_id, name) VALUES (1, 'Genre_1'), (2, 'Genre_2'), (3, 'Genre_3');

MERGE INTO books KEY (book_id, title, genre_id, author_id) VALUES (1, 'BookTitle_1', 1, 1), (2, 'BookTitle_2', 2, 2), (3, 'BookTitle_3', 3, 3);

MERGE INTO comments KEY (comment_id, text, book_id) VALUES (1, 'Super', 1), (2, 'Awesome', 1)
