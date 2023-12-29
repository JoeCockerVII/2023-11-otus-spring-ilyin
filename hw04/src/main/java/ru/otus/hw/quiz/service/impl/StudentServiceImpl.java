package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.quiz.domain.Student;
import ru.otus.hw.quiz.service.LocalizedIOService;
import ru.otus.hw.quiz.service.StudentService;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final LocalizedIOService ioService;

    @Override
    public Student determineCurrentStudent() {
        var firstName = ioService.readStringWithPromptLocalized("user.firstName");
        var lastName = ioService.readStringWithPromptLocalized("user.lastName");
        return new Student(firstName, lastName);
    }
}
