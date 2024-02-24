package ru.otus.hw.quiz.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.quiz.config.TestFileNameProvider;
import ru.otus.hw.quiz.domain.Question;
import ru.otus.hw.quiz.dao.dto.QuestionDto;
import ru.otus.hw.quiz.exceptions.QuestionReadException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final TestFileNameProvider testFileNameProvider;

    @Override
    public List<Question> findAll() {

        try (var input = new InputStreamReader(Objects.requireNonNull(
                getClass().getClassLoader().getResourceAsStream(testFileNameProvider.getTestFileName())))) {

            CsvToBean<QuestionDto> bean = new CsvToBeanBuilder<QuestionDto>(input)
                    .withType(QuestionDto.class)
                    .withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withSeparator(';')
                    .build();

            return bean.parse()
                    .stream()
                    .map(QuestionDto::toDomainObject)
                    .collect(Collectors.toList());
        } catch (IllegalStateException | IOException e) {
            throw new QuestionReadException("Questions failed to read", e);
        }
    }
}
