import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.hw.quiz.config.TestFileNameProvider;
import ru.otus.hw.quiz.dao.CsvQuestionDao;
import ru.otus.hw.quiz.domain.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TestServiceImplTest {

    private static final String QUESTIONS_FILE_NAME = "questionsTest.csv";

    private final TestFileNameProvider mockTestFileNameProvider = Mockito.mock(TestFileNameProvider.class);
    private final CsvQuestionDao csvQuestionDao = new CsvQuestionDao(mockTestFileNameProvider);

    @Test
    @DisplayName("Test: check correct answer for question from file")
    void testFirstQuestionFromCSVFile() {

        var expectedAnswer = "Nepal";

        when(mockTestFileNameProvider.getTestFileName()).thenReturn(QUESTIONS_FILE_NAME);

        var actualAnswer = csvQuestionDao.findAll().get(0).answers()
                .stream()
                .filter(Answer::isCorrect).toList().get(0).text();

        assertEquals(expectedAnswer, actualAnswer);
    }

}