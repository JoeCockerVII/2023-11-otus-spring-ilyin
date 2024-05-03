package ru.otus.hw.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw.feign.AuthorServiceProxy;
import ru.otus.hw.models.dto.AuthorDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorServiceProxy authorService;

    @GetMapping("/authors")
    public List<AuthorDto> getAuthors() {
        return authorService.findAll();
    }


}
