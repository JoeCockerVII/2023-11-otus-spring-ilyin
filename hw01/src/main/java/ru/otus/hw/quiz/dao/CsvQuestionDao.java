package ru.otus.hw.quiz.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.hw.quiz.domain.Question;
import ru.otus.hw.quiz.dao.dto.QuestionDto;
import ru.otus.hw.quiz.exceptions.QuestionReadException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

    private final String filePath;

    @Override
    public List<Question> findAll() {

        InputStream in = Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(filePath));
        try (InputStreamReader input = new InputStreamReader(in)) {
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
