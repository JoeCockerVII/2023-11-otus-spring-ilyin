INSERT INTO authors (full_name)
    VALUES ('Author_1'), ('Author_2'), ('Author_3') ON CONFLICT (author_id) DO NOTHING;

INSERT INTO genres (name)
    VALUES ('Genre_1'), ('Genre_2'), ('Genre_3') ON CONFLICT (genre_id) DO NOTHING;

INSERT INTO books (title, genre_id, author_id)
    VALUES ('BookTitle_1', 1, 1), ('BookTitle_2', 2, 2), ('BookTitle_3', 3, 3) ON CONFLICT (book_id) DO NOTHING;