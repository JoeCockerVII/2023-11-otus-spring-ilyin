package ru.otus.hw.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.hw.feign.BookServiceProxy;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.services.BookService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookServiceProxy bookServiceProxy;

    @Override
    public List<BookDto> findAll() {
        return bookServiceProxy.findAll();
    }

    @Override
    public BookDto create(BookCreateDto dto) {
        return bookServiceProxy.create(dto);
    }

    @Override
    public BookDto update(@PathVariable("id") long id, @Valid @RequestBody BookUpdateDto bookUpdateDto) {
        bookUpdateDto.setId(id);
        return bookServiceProxy.update(id,bookUpdateDto);
    }

    @Override
    public void deleteById(long id) {
        bookServiceProxy.deleteById(id);
    }
}
