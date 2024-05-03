create table if not exists authors(
    author_id SERIAL PRIMARY KEY NOT NULL,
    full_name varchar(255)
);

create table if not exists genres(
    genre_id SERIAL PRIMARY KEY NOT NULL,
    name varchar(255)
);

create table if not exists books(
    book_id bigserial,
    title varchar(255),
    author_id bigint references authors(author_id) on delete cascade,
    genre_id bigint references genres(genre_id) on delete cascade,
    primary key (book_id)
);

create table if not exists comments(
    comment_id SERIAL PRIMARY KEY NOT NULL,
    text varchar(255),
    book_id bigint references books(book_id) on delete cascade
);


-- comment_id bigserial,
--     text varchar(255),
--     primary key (comment_id),
-- --     book_id bigint references books(book_id) on delete cascade
--     FOREIGN KEY (book_id) REFERENCES books (book_id) on delete cascade on update cascade