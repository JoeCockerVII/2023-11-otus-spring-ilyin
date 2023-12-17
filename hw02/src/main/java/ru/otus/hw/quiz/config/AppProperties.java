package ru.otus.hw.quiz.config;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Setter
public class AppProperties implements TestConfig, TestFileNameProvider {

    // внедрить свойство из application.properties //todo
    @Value("$(test.rightAnswersCountToPass)")
    private int rightAnswersCountToPass;

    // внедрить свойство из application.properties //todo
    @Value("$(test.fileName)")
    private String testFileName;

    @Override
    public int getRightAnswersCountToPass() {
        return rightAnswersCountToPass;
    }

    @Override
    public String getTestFileName() {
        return testFileName;
    }
}
