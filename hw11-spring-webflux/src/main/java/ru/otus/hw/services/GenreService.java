package ru.otus.hw.services;

import reactor.core.publisher.Flux;
import ru.otus.hw.models.dto.GenreDto;

public interface GenreService {
    Flux<GenreDto> findAll();
}
