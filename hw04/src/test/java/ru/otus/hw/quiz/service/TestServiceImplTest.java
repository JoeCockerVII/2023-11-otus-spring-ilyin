package ru.otus.hw.quiz.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import ru.otus.hw.quiz.dao.QuestionDao;
import ru.otus.hw.quiz.domain.Answer;
import ru.otus.hw.quiz.domain.Question;
import ru.otus.hw.quiz.domain.Student;
import ru.otus.hw.quiz.service.impl.TestServiceImpl;

import java.util.List;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test service")
@ContextConfiguration()
@SpringBootTest()
class TestServiceImplTest {

    private final LocalizedIOService ioServiceMock = Mockito.mock(LocalizedIOService.class);

    private final QuestionDao questionDaoMock = Mockito.mock(QuestionDao.class);

    @InjectMocks
    private TestServiceImpl testService;

    @Test
    @DisplayName("Test: check correct answer for service test")
    void correctAnswerPositiveTest() {

        Answer answer1 = new Answer("Nepal", true);
        Answer answer2 = new Answer("China", false);

        Question question = new Question("In which country is Mount Everest?", List.of(answer1, answer2));

        var expectedQuestions = List.of(question);

        Mockito.when(questionDaoMock.findAll()).thenReturn(expectedQuestions);
        Mockito.when(ioServiceMock.readIntForRangeWithPromptLocalized(1, 2,
                "answer.enter", "incorrectInput")).thenReturn(1);

        var result = testService.executeTestFor(new Student("FirstName", "SecondName"));
        Assertions.assertEquals(1, result.getRightAnswersCount());
    }

}