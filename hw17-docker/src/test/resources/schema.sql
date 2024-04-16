create table if not exists authors(
    author_id bigserial,
    full_name varchar(255),
    primary key (author_id)
);

create table if not exists genres(
    genre_id bigserial,
    name varchar(255),
    primary key (genre_id)
);

create table if not exists books(
    book_id bigserial,
    title varchar(255),
    author_id bigint references authors(author_id) on delete cascade,
    genre_id bigint references genres(genre_id) on delete cascade,
    primary key (book_id)
);

create table if not exists comments(
    comment_id bigserial,
    text varchar(255),
    book_id bigint references books(book_id) on delete cascade,
    primary key (comment_id)
);

create table if not exists roles (
    id bigserial,
    name varchar(255),
    primary key (id)
);

create table if not exists users (
    user_id bigserial,
    username varchar(255),
    password varchar(255),
    role_id bigint references roles(id),
    primary key (user_id)
);
