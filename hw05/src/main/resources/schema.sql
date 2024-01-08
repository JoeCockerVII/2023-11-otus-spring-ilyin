drop table authors if exists;
create table authors (
    id bigserial,
    full_name varchar(255),
    primary key (id)
);

drop table genres if exists;
create table genres (
    id bigserial,
    name varchar(255),
    primary key (id)
);

drop table books if exists;
create table books (
    id bigserial,
    title varchar(255),
    author_id bigint references authors (id) on delete cascade,
    genre_id bigint references genres(id) on delete cascade,
    primary key (id)
);