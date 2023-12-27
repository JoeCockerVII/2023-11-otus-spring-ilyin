package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.quiz.config.TestConfig;
import ru.otus.hw.quiz.domain.TestResult;
import ru.otus.hw.quiz.service.LocalizedIOService;
import ru.otus.hw.quiz.service.ResultService;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final TestConfig testConfig;

    private final LocalizedIOService ioService;

    @Override
    public void showResult(TestResult testResult) {
        ioService.printLine("");
        ioService.printLineLocalized("showResult.testResult");
        ioService.printFormattedLineLocalized("showResult.student", testResult.getStudent().getFullName());
        ioService.printFormattedLineLocalized("showResult.answered.questions", testResult.getAnsweredQuestions().size());
        ioService.printFormattedLineLocalized("showResult.right.answers.count", testResult.getRightAnswersCount());

        if (testResult.getRightAnswersCount() >= testConfig.getRightAnswersCountToPass()) {
            ioService.printLineLocalized("showResult.succeed");
            return;
        }
        ioService.printLineLocalized("showResult.fail");
    }
}
