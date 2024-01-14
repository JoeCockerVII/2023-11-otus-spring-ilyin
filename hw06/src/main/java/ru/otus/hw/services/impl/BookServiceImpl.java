package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.models.Book;
import ru.otus.hw.services.BookService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    @Override
    public Optional<Book> findById(long id) {
        return Optional.empty();//todo
    }

    @Override
    public List<Book> findAll() {
        return null;//todo
    }

    @Override
    public Book insert(String title, long authorId, long genreId) {
        return null;//todo
    }

    @Override
    public Book update(long id, String title, long authorId, long genreId) {
        return null;//todo
    }

    @Override
    public void deleteById(long id) {
        //todo
    }
}
