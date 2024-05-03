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

@FeignClient(name = "library-server", contextId = "library-books")
public interface BookServiceProxy {

    @CircuitBreaker(name = "library-server", fallbackMethod = "getDefaultBooks")
    @GetMapping("/books")
    List<BookDto> findAll();

    @CircuitBreaker(name = "library-server")
    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    BookDto create(@Valid @RequestBody BookCreateDto bookCreateDto);

    @CircuitBreaker(name = "library-server")
    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") long id);

    @CircuitBreaker(name = "library-server")
    @PutMapping("/books/{id}")
    BookDto update(@PathVariable("id") long id,
                      @Valid @RequestBody BookUpdateDto bookUpdateDto);

    default List<BookDto> getDefaultBooks(Throwable throwable) {
        return List.of(
                new BookDto(101L,"Title_101", new AuthorDto(101L,"Author_101"), new GenreDto(100L, "Genre_101")),
                new BookDto(102L,"Title_102", new AuthorDto(102L,"Author_102"), new GenreDto(102L, "Genre_102")),
                new BookDto(103L,"Title_103", new AuthorDto(103L,"Author_103"), new GenreDto(103L, "Genre_103"))
        );
    }

}