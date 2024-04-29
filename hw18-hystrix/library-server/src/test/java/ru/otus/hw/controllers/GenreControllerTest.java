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
import ru.otus.hw.services.GenreService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService genreService;

    @InjectMocks
    private TestHelper testHelper;

    @Test
    @DisplayName("Should return Genre list")
    void returnGenreList() throws Exception {
        var genres = testHelper.getDbGenresDto();
        given(genreService.findAll()).willReturn(genres);

        mvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(genres)));
    }

}