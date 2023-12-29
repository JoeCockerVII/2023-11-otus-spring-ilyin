package ru.otus.hw.quiz.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw.quiz.service.impl.TestRunnerServiceImpl;

@ShellComponent
public class ShellCommands {

    private final TestRunnerServiceImpl runner;

    @Autowired
    public ShellCommands(TestRunnerServiceImpl runner) {
        this.runner = runner;
    }

    @ShellMethod(value = "Start", key = {"s","start"})
    public void start() {
        runner.run();
    }

    @ShellMethod(value = "Application name", key = {"a","app","appName"})
    public String appName() {
        return "Application for students Test Quiz";
    }

}
