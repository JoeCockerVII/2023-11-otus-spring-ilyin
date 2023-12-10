import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.hw.quiz.dao.CsvQuestionDao;
import ru.otus.hw.quiz.domain.Answer;
import ru.otus.hw.quiz.domain.Question;


import java.util.List;


@ExtendWith(MockitoExtension.class)
class TestServiceImplTest {

    @Mock
    private CsvQuestionDao csvQuestionDao;

    @Test
    public void runTest() {
        Answer answer1 = new Answer("2", false);
        Answer answer2 = new Answer("3", true);
        Answer answer3 = new Answer("1", false);
        Question question = new Question("How many Newton's laws exist?", List.of(answer1, answer2, answer3));

        List<Question> expectedQuestions = List.of(question);

        Mockito.when(csvQuestionDao.findAll()).thenReturn(expectedQuestions);

        List<Question> actualQuestions = csvQuestionDao.findAll();

        Assertions.assertEquals(expectedQuestions, actualQuestions);
    }}