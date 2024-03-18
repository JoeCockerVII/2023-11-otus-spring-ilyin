package ru.otus.hw.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw.TestHelper;
import ru.otus.hw.exceptions.NotFoundException;
import ru.otus.hw.models.dto.BookDto;
import ru.otus.hw.security.SecurityConfiguration;
import ru.otus.hw.services.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@Import(SecurityConfiguration.class)
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

    @InjectMocks
    private TestHelper testHelper;

    private List<BookDto> books;

    @BeforeEach
    void setUp(){
        books = testHelper.getDbBooks();
    }

    @Test
    @DisplayName("Should get all books")
    @WithMockUser(username = "user", roles = "USER")
    void shouldGetAllBooks() throws Exception {
        given(bookService.findAll()).willReturn(books);
        var content = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"))
                .andExpect(model().attributeExists("books"))
                .andReturn().getResponse().getContentAsString();

        assertTrue(content.contains(books.get(0).getTitle()));
        assertTrue(content.contains(books.get(1).getTitle()));
    }

    @Test
    @DisplayName("Should save book")
    @WithMockUser(username = "user", roles = "USER")
    void shouldSaveBook() throws Exception {
        mockMvc.perform(post("/create")
                        .param("title", books.get(0).getTitle())
                        .param("id", String.valueOf(books.get(0).getId()))
                        .param("authorId", String.valueOf(books.get(0).getAuthor().getId()))
                        .param("genreId", String.valueOf(books.get(0).getGenre().getId())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should edit book")
    @WithMockUser(username = "user", roles = "USER")
    void shouldEditBook() throws Exception {
        mockMvc.perform(post("/edit")
                        .param("title", books.get(0).getTitle())
                        .param("id", String.valueOf(books.get(0).getId()))
                        .param("author.id", String.valueOf(books.get(0).getAuthor().getId()))
                        .param("author.fullName", String.valueOf(books.get(0).getAuthor().getFullName()))
                        .param("genre.id", String.valueOf(books.get(0).getGenre().getId()))
                        .param("genre.name", String.valueOf(books.get(0).getGenre().getName()))
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));
    }

    @Test
    @DisplayName("Should delete book")
    @WithMockUser(username = "user", roles = "USER")
    void shouldCorrectDeleteBook() throws Exception {
        long id = 1L;
        mockMvc.perform(delete("/delete/{id}", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @DisplayName("Should redirect when empty title")
    @WithMockUser(username = "user", roles = "USER")
    void shouldRedirectWhenEmptyTitle() throws Exception {
        mockMvc.perform(post("/edit")
                        .param("title","")
                        .param("id", String.valueOf(books.get(0).getId()))
                        .param("author.id", String.valueOf(books.get(0).getAuthor().getId()))
                        .param("author.fullName", String.valueOf(books.get(0).getAuthor().getFullName()))
                        .param("genre.id", String.valueOf(books.get(0).getGenre().getId()))
                        .param("genre.name", String.valueOf(books.get(0).getGenre().getName()))
                )
                .andExpect(view().name("edit"));
    }

    @Test
    @DisplayName("Should redirect when incorrect id")
    @WithMockUser(username = "user", roles = "USER")
    void shouldRedirectWhenIncorrectId() throws Exception {
        mockMvc.perform(post("/edit")
                        .param("title", books.get(0).getTitle())
                        .param("id", "")
                        .param("author.id", String.valueOf(books.get(0).getAuthor().getId()))
                        .param("author.fullName", String.valueOf(books.get(0).getAuthor().getFullName()))
                        .param("genre.id", String.valueOf(books.get(0).getGenre().getId()))
                        .param("genre.name", String.valueOf(books.get(0).getGenre().getName()))
                )
                .andExpect(view().name("edit"));
    }

    @Test
    @DisplayName("Should get redirection on edit")
    void unauthorizedTestOnEdit() throws Exception {
        given(bookService.findById(1L)).willThrow(new NotFoundException());
        mockMvc.perform(get("/edit")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @DisplayName("Should get redirection on create")
    void unauthorizedTestOnCreate() throws Exception {
        given(bookService.findById(1L)).willThrow(new NotFoundException());
        mockMvc.perform(get("/create")
                        .param("id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }


}