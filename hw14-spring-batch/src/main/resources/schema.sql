create table if not exists authors(
    author_id varchar(255),
    full_name varchar(255),
    primary key (author_id)
);

create table if not exists genres(
    genre_id varchar(255),
    name varchar(255),
    primary key (genre_id)
);

create table if not exists books(
    book_id varchar(255),
    title varchar(255),
    author_id varchar(255) references authors(author_id) on delete cascade,
    genre_id varchar(255) references genres(genre_id) on delete cascade,
    primary key (book_id)
);

create table if not exists comments(
    comment_id varchar(255),
    text varchar(255),
    book_id varchar(255) references books(book_id) on delete cascade,
    primary key (comment_id)
);