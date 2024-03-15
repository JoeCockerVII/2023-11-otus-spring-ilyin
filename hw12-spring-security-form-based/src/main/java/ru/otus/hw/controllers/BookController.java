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
import ru.otus.hw.models.dto.BookCreateDto;
import ru.otus.hw.models.dto.BookUpdateDto;
import ru.otus.hw.services.AuthorService;
import ru.otus.hw.services.BookService;
import ru.otus.hw.services.GenreService;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @GetMapping("/")
    public String listPage(Model model) {

        model.addAttribute("books", bookService.findAll());

        return "list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {

        model.addAttribute("book", new BookCreateDto());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());

        return "create";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {

        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());

        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable Long id, Model model) {

        model.addAttribute("book", bookService.findById(id));

        return "delete";
    }


    @PostMapping("/create")
    public String saveBook(@Valid @ModelAttribute("book") BookCreateDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());
            return "create";
        }

        bookService.create(dto);

        return "redirect:/";
    }


    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("book") BookUpdateDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {

            model.addAttribute("authors", authorService.findAll());
            model.addAttribute("genres", genreService.findAll());

            return "edit";
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
