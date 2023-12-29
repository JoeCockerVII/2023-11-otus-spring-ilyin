package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.hw.quiz.dao.QuestionDao;
import ru.otus.hw.quiz.domain.Answer;
import ru.otus.hw.quiz.domain.Question;
import ru.otus.hw.quiz.domain.Student;
import ru.otus.hw.quiz.domain.TestResult;
import ru.otus.hw.quiz.service.LocalizedIOService;
import ru.otus.hw.quiz.service.TestService;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final LocalizedIOService ioService;

    private final QuestionDao questionDao;

    @Override
    public TestResult executeTestFor(Student student) {
        ioService.printLine("");
        ioService.printLineLocalized("answer.question");
        ioService.printLine("");
        var questions = questionDao.findAll();
        var testResult = new TestResult(student);

        for (var question: questions) {
            var isAnswerValid = getStudentAnswer(question);
            testResult.applyAnswer(question, isAnswerValid);
        }
        return testResult;
    }

    public boolean getStudentAnswer(Question question) {
        var answers = question.answers();

        ioService.printLine("\n" + question.text());
        int answerNumber = 0;
        for (Answer answer : question.answers()) {
            ioService.printFormattedLine("%d. %s",++answerNumber,answer.text());
        }

        int studentAnswerNumber = ioService.readIntForRangeWithPromptLocalized(1,answers.size(),
                "answer.enter", "incorrectInput");

        return answers.get(studentAnswerNumber - 1).isCorrect();
    }

}
