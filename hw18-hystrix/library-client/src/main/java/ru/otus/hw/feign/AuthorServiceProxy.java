package ru.otus.hw.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.hw.models.dto.AuthorDto;


import java.util.List;

@FeignClient(name = "library-server", contextId = "library-authors")
public interface AuthorServiceProxy {

    @CircuitBreaker(name = "library-server")
    @GetMapping("/authors")
    List<AuthorDto> findAll();

}