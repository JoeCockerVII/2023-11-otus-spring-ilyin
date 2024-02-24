package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import ru.otus.hw.quiz.service.ResultService;
import ru.otus.hw.quiz.service.StudentService;
import ru.otus.hw.quiz.service.TestService;

@Service
@RequiredArgsConstructor
public class TestRunnerServiceImpl implements CommandLineRunner {

    private final TestService testService;

    private final StudentService studentService;

    private final ResultService resultService;

    @Override
    public void run(String... args) {
        var student = studentService.determineCurrentStudent();
        var testResult = testService.executeTestFor(student);
        resultService.showResult(testResult);
    }
}
