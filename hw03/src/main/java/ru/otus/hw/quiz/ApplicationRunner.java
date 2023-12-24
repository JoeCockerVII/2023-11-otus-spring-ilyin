package ru.otus.hw.quiz;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.otus.hw.quiz.service.TestRunnerService;

@RequiredArgsConstructor
@Component
public class ApplicationRunner implements CommandLineRunner {

    private final TestRunnerService runnerService;

    @Override
    public void run(String... args) {
        runnerService.run();
    }

}
