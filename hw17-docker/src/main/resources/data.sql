INSERT INTO authors (full_name)
    VALUES ('Author_1'), ('Author_2'), ('Author_3') ON CONFLICT (author_id) DO NOTHING;

INSERT INTO genres (name)
    VALUES ('Genre_1'), ('Genre_2'), ('Genre_3') ON CONFLICT (genre_id) DO NOTHING;

INSERT INTO books (title, genre_id, author_id)
    VALUES ('BookTitle_1', 1, 1), ('BookTitle_2', 2, 2), ('BookTitle_3', 3, 3) ON CONFLICT (book_id) DO NOTHING;

INSERT INTO comments (text, book_id)
    VALUES ('Super', 1), ('Awesome', 1) ON CONFLICT (comment_id) DO NOTHING;

INSERT INTO roles (name)
    VALUES ('ADMIN'),  ('USER') ON CONFLICT (id) DO NOTHING;

INSERT INTO users (username, password, role_id)
    VALUES  ('user', '$2a$12$SIs.hJKuWxsVlsPNVN53/eKMEb4Ks97ikMe64hCJrr8GKPQt2hbIK',2),
            ('admin', '$2a$12$we6cdYeikeAH.cpaOwzum.xtm1f2jHLHmaLrtdEw5JpnoJ/8x4Iz2',1)
    ON CONFLICT (user_id) DO NOTHING;