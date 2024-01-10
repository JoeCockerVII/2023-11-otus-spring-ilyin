merge into authors(author_id, full_name)
    values (1, 'Author_1'), (2, 'Author_2'), (3, 'Author_3');

merge into genres(genre_id, name)
    values (1, 'Genre_1'), (2, 'Genre_2'), (3, 'Genre_3');

merge into books(book_id, title, author_id, genre_id)
    values (1, 'BookTitle_1', 1, 1), (2, 'BookTitle_2', 2, 2), (3, 'BookTitle_3', 3, 3);
