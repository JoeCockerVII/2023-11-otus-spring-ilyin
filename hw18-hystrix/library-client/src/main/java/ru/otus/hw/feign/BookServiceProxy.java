package ru.otus.hw.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.models.dto.GenreDto;

import java.util.List;

@FeignClient(name = "library")
public interface BookServiceProxy {

    @CircuitBreaker(name = "library", fallbackMethod = "getDefaultBooks")
    @GetMapping("/books")
    List<BookDto> findAll();

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    BookDto create(@Valid @RequestBody BookCreateDto bookCreateDto);

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") long id);

    @PutMapping("/books/{id}")
    BookDto update(@PathVariable("id") long id,
                      @Valid @RequestBody BookUpdateDto bookUpdateDto);

    default List<BookDto> getDefaultBooks(Throwable throwable) {
        return List.of(new BookDto(100L,"Title_100", new AuthorDto(100L,"Author_100"),
                new GenreDto(100L, "Genre_100")));
    }

}