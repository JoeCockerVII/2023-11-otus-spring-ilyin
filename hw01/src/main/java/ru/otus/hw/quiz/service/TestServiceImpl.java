package ru.otus.hw.quiz.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.quiz.dao.QuestionDao;
import ru.otus.hw.quiz.domain.Answer;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        var questions = questionDao.findAll();

        questions.forEach(question -> {
            ioService.printLine("\n" + question.text());
            int answerNumber = 0;
            for (Answer answer : question.answers()) {
                ioService.printFormattedLine("%d. %s | %s",++answerNumber,answer.text(),answer.isCorrect());
            }
        });

    }
}
