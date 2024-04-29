package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.feign.GenreServiceProxy;
import ru.otus.hw.models.dto.GenreDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {

    private final GenreServiceProxy genreService;

    @GetMapping("/genres")
    public List<GenreDto> getGenres() {
        return genreService.findAll();
    }

}
