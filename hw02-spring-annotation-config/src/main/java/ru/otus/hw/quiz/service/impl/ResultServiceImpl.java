package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.quiz.config.TestConfig;
import ru.otus.hw.quiz.domain.TestResult;
import ru.otus.hw.quiz.service.IOService;
import ru.otus.hw.quiz.service.ResultService;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final TestConfig testConfig;

    private final IOService ioService;

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printLine("Test results: ");
        ioService.printFormattedLine("Student: %s", testResult.getStudent().getFullName());
        ioService.printFormattedLine("Answered questions count: %d", testResult.getAnsweredQuestions().size());
        ioService.printFormattedLine("Right answers count: %d", testResult.getRightAnswersCount());

        if (testResult.getRightAnswersCount() >= testConfig.getRightAnswersCountToPass()) {
            ioService.printLine("Congratulations! You passed test!");
            return;
        }
        ioService.printLine("Sorry. You fail test.");
    }
}
