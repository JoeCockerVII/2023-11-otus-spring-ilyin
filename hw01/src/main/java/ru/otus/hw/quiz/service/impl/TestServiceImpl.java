package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.quiz.service.IOService;
import ru.otus.hw.quiz.service.TestService;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        // Получить вопросы из дао и вывести их с вариантами ответов //todo
    }
}
