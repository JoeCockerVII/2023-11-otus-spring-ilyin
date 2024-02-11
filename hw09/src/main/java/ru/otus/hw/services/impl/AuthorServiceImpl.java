package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.models.Author;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.services.AuthorService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Author> findById(long id) {
        return authorRepository.findById(id);
    }
}