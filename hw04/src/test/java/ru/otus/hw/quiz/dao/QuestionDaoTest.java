package ru.otus.hw.quiz.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.otus.hw.quiz.config.TestConfiguration;
import ru.otus.hw.quiz.config.TestFileNameProvider;
import ru.otus.hw.quiz.domain.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test dao")
@ContextConfiguration()
@SpringBootTest()
class QuestionDaoTest {

    private static final String QUESTIONS_FILE_NAME = "questionsTest.csv";

    private final TestFileNameProvider mockTestFileNameProvider = Mockito.mock(TestFileNameProvider.class);

    @InjectMocks
    private CsvQuestionDao csvQuestionDao;

    @Test
    @DisplayName("Test: check correct answer for question from file")
    void correctAnswerPositiveTest() {

        var expectedAnswer = "Nepal";

        when(mockTestFileNameProvider.getTestFileName()).thenReturn(QUESTIONS_FILE_NAME);

        var actualAnswer = csvQuestionDao.findAll().get(0).answers()
                .stream()
                .filter(Answer::isCorrect).toList().get(0).text();

        assertEquals(expectedAnswer, actualAnswer);
    }

}