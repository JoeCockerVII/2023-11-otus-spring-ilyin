package ru.otus.hw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw.models.dto.AuthorDto;
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.models.dto.GenreDto;
import ru.otus.hw.services.AuthorService;
import ru.otus.hw.services.BookService;
import ru.otus.hw.services.GenreService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/")
    public String listPage(Model model) {
        var books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {

        var authors = authorService.findAll();
        var genres = genreService.findAll();

        model.addAttribute("book", new BookCreateDto());
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);

        return "create";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {

        var book = bookService.findById(id);

        List<AuthorDto> authors = authorService.findAll();
        List<GenreDto> genres = genreService.findAll();

        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);

        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable Long id, Model model) {
        var book = bookService.findById(id);
        model.addAttribute("book", book);
        return "delete";
    }


    @PostMapping("/create")
    public String saveBook(@Valid @ModelAttribute("book") BookCreateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }

        bookService.create(dto);

        return "redirect:/";
    }


    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") BookUpdateDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        bookService.update(dto);

        return "redirect:/";
    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookService.deleteById(id);
        return "redirect:/";
    }



}
