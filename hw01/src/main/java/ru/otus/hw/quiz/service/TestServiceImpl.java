package ru.otus.hw.quiz.service;

import lombok.RequiredArgsConstructor;
import ru.otus.hw.quiz.dao.QuestionDao;
import ru.otus.hw.quiz.domain.Answer;
import ru.otus.hw.quiz.domain.Question;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;

    private final QuestionDao questionDao;

    @Override
    public void executeTest() {
        ioService.printLine("");
        ioService.printFormattedLine("Please answer the questions below%n");
        List<Question> questions = questionDao.findAll();

        for (Question question : questions) {
            List<String> answerList = question.answers().stream().map(Answer::text).toList();
            String answers = String.join("\n- ", answerList);
            ioService.printFormattedLine("%s",question.text());
            ioService.printFormattedLine("- %s\n",answers);
        }

    }
}
