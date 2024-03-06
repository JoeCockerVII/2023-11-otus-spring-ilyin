package ru.otus.hw.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.mappers.AuthorMapper;
import ru.otus.hw.repositories.AuthorRepository;
import ru.otus.hw.services.AuthorService;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    private final AuthorMapper mapper;

    @Override
    public Flux<AuthorDto> findAll() {
        return authorRepository.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<AuthorDto> findById(String id) {
        return authorRepository.findById(id).map(mapper::toDto);
    }
}