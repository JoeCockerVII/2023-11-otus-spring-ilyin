package ru.otus.hw.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.hw.models.dto.GenreDto;

import java.util.List;

@FeignClient(name = "library-server", contextId = "library-genres")
public interface GenreServiceProxy {

    @CircuitBreaker(name = "library-server")
    @GetMapping("/genres")
    List<GenreDto> findAll();

}