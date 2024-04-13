create table if not exists authors(
    author_id SERIAL PRIMARY KEY NOT NULL,
    full_name varchar(255)
);

create table if not exists genres(
    genre_id SERIAL PRIMARY KEY NOT NULL,
    name varchar(255)
);

create table if not exists books(
    book_id SERIAL PRIMARY KEY NOT NULL,
    title varchar(255),
    author_id bigint references authors(author_id) on delete cascade,
    genre_id bigint references genres(genre_id) on delete cascade
);

create table if not exists comments(
    comment_id SERIAL PRIMARY KEY NOT NULL,
    text varchar(255),
    book_id bigint references books(book_id) on delete cascade
);

create table if not exists roles (
    id SERIAL PRIMARY KEY NOT NULL,
    name varchar(255)
);

create table if not exists users (
    user_id SERIAL PRIMARY KEY NOT NULL,
    username varchar(255),
    password varchar(255),
    role_id bigint references roles(id)
);
