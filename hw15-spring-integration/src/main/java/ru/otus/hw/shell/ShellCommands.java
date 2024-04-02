package ru.otus.hw.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.services.CutletService;

@RequiredArgsConstructor
@ShellComponent
public class ShellCommands {
    private final CutletService cutletService;

    @ShellMethod(value = "Cutlet creation", key = {"cutlet", "c"})
    public void burgerCreation() {
        cutletService.startGenerateCutletLoop();
    }

}
