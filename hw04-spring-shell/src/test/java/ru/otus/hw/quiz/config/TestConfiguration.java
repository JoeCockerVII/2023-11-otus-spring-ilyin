package ru.otus.hw.quiz.config;

import lombok.RequiredArgsConstructor;
import org.mockito.Mock;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TestConfiguration {

    @Mock
    private CommandLineRunner runner;
}
