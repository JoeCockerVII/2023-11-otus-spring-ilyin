package ru.otus.hw.commands;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw.converters.CommentConverter;
import ru.otus.hw.services.CommentService;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@ShellComponent
public class CommentCommands {

    private final CommentService commentService;

    private final CommentConverter commentConverter;

    @ShellMethod(value = "Find by id", key = "cbid")
    public String findCommentById(@ShellOption("id") String id) {
        return commentService.findById(id).stream()
                .map(commentConverter::commentToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Find by book id", key = "cbbid")
    public String findCommentByBookId(@ShellOption("bookId") String bookId) {
        return commentService.findByBookId(bookId).stream()
                .map(commentConverter::commentToString)
                .collect(Collectors.joining("," + System.lineSeparator()));
    }

    @ShellMethod(value = "Insert comment", key = "cins")
    public String insertComment(@ShellOption("text") String text, @ShellOption("bookId") String bookId) {
        var comment = commentService.create(text, bookId);
        return commentConverter.commentToString(comment);
    }

    @ShellMethod(value = "Update comment", key = "cupd")
    public String updateComment(@ShellOption("id") String id, @ShellOption("text") String text) {
        var comment = commentService.update(id, text);
        return commentConverter.commentToString(comment);
    }

    @ShellMethod(value = "Delete comment", key = "cdel")
    public void deleteComment(@ShellOption("id") String id) {
        commentService.deleteById(id);
    }

}
