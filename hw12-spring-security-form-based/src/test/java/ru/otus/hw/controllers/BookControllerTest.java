package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.models.dto.GenreDto;
import ru.otus.hw.security.SecurityConfiguration;
import ru.otus.hw.services.*;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
@WithMockUser(username = "user", roles = "USER")
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorService authorService;

    @MockBean
    private GenreService genreService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserService service;

    @Test
    @DisplayName("Should get all books")
    void shouldGetAllBooks() throws Exception {
        given(bookService.findAll()).willReturn(getDbBooks());
        var content = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("books"))
                .andReturn().getResponse().getContentAsString();

        assertTrue(content.contains(getDbBooks().get(0).getTitle()));
        assertTrue(content.contains(getDbBooks().get(1).getTitle()));
    }

    @Test
    @DisplayName("Should save book")
    void shouldSaveBook() throws Exception {
        mockMvc.perform(post("/create")
                        .param("title", getDbBooks().get(0).getTitle())
                        .param("id", String.valueOf(getDbBooks().get(0).getId()))
                        .param("authorId", String.valueOf(getDbBooks().get(0).getAuthor().getId()))
                        .param("genreId", String.valueOf(getDbBooks().get(0).getGenre().getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should edit book")
    void shouldEditBook() throws Exception {
        mockMvc.perform(post("/edit")
                        .param("title", getDbBooks().get(0).getTitle())
                        .param("id", String.valueOf(getDbBooks().get(0).getId()))
                        .param("author.id", String.valueOf(getDbBooks().get(0).getAuthor().getId()))
                        .param("author.fullName", String.valueOf(getDbBooks().get(0).getAuthor().getFullName()))
                        .param("genre.id", String.valueOf(getDbBooks().get(0).getGenre().getId()))
                        .param("genre.name", String.valueOf(getDbBooks().get(0).getGenre().getName()))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should delete book")
    void shouldCorrectDeleteBook() throws Exception {
        long id = 1L;
        mockMvc.perform(delete("/delete/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("Should redirect when empty title")
    void shouldRedirectWhenEmptyTitle() throws Exception {
        mockMvc.perform(post("/edit")
                        .param("title","")
                        .param("id", String.valueOf(getDbBooks().get(0).getId()))
                        .param("author.id", String.valueOf(getDbBooks().get(0).getAuthor().getId()))
                        .param("author.fullName", String.valueOf(getDbBooks().get(0).getAuthor().getFullName()))
                        .param("genre.id", String.valueOf(getDbBooks().get(0).getGenre().getId()))
                        .param("genre.name", String.valueOf(getDbBooks().get(0).getGenre().getName()))
                )
                .andExpect(view().name("edit"));
    }

    @Test
    @DisplayName("Should redirect when incorrect id")
    void shouldRedirectWhenIncorrectId() throws Exception {
        mockMvc.perform(post("/edit")
                        .param("title", getDbBooks().get(0).getTitle())
                        .param("id", "")
                        .param("author.id", String.valueOf(getDbBooks().get(0).getAuthor().getId()))
                        .param("author.fullName", String.valueOf(getDbBooks().get(0).getAuthor().getFullName()))
                        .param("genre.id", String.valueOf(getDbBooks().get(0).getGenre().getId()))
                        .param("genre.name", String.valueOf(getDbBooks().get(0).getGenre().getName()))
                )
                .andExpect(view().name("edit"));
    }


    private static List<AuthorDto> getDbAuthors() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new AuthorDto(id.longValue(), "Author_" + id))
                .toList();
    }

    private static List<GenreDto> getDbGenres() {
        return IntStream.range(1, 4).boxed()
                .map(id -> new GenreDto(id.longValue(), "Genre_" + id))
                .toList();
    }

    private static List<BookDto> getDbBooks(List<AuthorDto> dbAuthors, List<GenreDto> dbGenres) {
        return IntStream.range(1, 4).boxed()
                .map(id -> new BookDto(id.longValue(), "BookTitle_" + id, dbAuthors.get(id - 1), dbGenres.get(id - 1)))
                .toList();
    }

    private static List<BookDto> getDbBooks() {
        var dbAuthors = getDbAuthors();
        var dbGenres = getDbGenres();
        return getDbBooks(dbAuthors, dbGenres);
    }

}