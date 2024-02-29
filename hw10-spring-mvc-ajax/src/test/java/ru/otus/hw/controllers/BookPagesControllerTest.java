package ru.otus.hw.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookPagesController.class)
class BookPagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Should return list view page")
    public void shouldReturnListPage() throws Exception {

        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"));
    }

    @Test
    @DisplayName("Should return edit view page")
    public void shouldReturnEditPage() throws Exception {

        mvc.perform(get("/edit/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attribute("id", 1L));
    }

    @Test
    @DisplayName("Should return create view page")
    public void shouldReturnCreatePage() throws Exception {

        mvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("create"));
    }

}