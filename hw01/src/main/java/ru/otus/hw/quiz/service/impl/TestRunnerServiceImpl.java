package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.quiz.service.TestRunnerService;
import ru.otus.hw.quiz.service.TestService;

@RequiredArgsConstructor
public class TestRunnerServiceImpl implements TestRunnerService {

    private final TestService testService;

    @Override
    public void run() {
        testService.executeTest();
    }
}
