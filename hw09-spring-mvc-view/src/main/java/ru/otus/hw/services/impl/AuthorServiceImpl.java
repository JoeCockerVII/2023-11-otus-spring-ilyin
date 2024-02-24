package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.mappers.AuthorMapper;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.services.AuthorService;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper mapper;

    @Transactional(readOnly = true)
    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public AuthorDto findById(long id) {
        return authorRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(NotFoundException::new);
    }
}