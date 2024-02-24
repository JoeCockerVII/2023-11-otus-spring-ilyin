package ru.otus.hw.quiz.service;

import ru.otus.hw.quiz.domain.Student;
import ru.otus.hw.quiz.domain.TestResult;

public interface TestService {
    TestResult executeTestFor(Student student);
}
