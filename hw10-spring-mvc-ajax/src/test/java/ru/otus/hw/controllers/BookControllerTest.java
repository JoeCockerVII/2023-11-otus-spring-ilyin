package ru.otus.hw.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.TestHelper;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.services.BookService;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @InjectMocks
    private TestHelper testHelper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should get book list")
    void shouldGetBookList() throws Exception {
        var books = testHelper.getDbBooks();

        given(bookService.findAll()).willReturn(books);

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(books)));
    }

    @Test
    @DisplayName("Should create book")
    void shouldCreateBook() throws Exception {

        var book = new BookCreateDto("Title",  1L, 1L);
        var expected = objectMapper.writeValueAsString(book);

        mvc.perform(post("/books")
                        .contentType(APPLICATION_JSON)
                        .content(expected))
                .andExpect(status().isOk());

        verify(bookService).create(book);
    }

    @Test
    @DisplayName("Should update book")
    void shouldUpdateBook() throws Exception {

        var book = new BookUpdateDto(1L, "Title",  1L, 1L);
        var expected = objectMapper.writeValueAsString(book);

        mvc.perform(put("/books/1")
                        .contentType(APPLICATION_JSON)
                        .content(expected))
                .andExpect(status().isOk());

        verify(bookService).update(book);
    }

    @Test
    @DisplayName("Should delete book")
    void shouldDeleteBook() throws Exception {
        mvc.perform(delete("/books/1"))
                .andExpect(status().isOk());
        verify(bookService, times(1)).deleteById(1L);
    }

}