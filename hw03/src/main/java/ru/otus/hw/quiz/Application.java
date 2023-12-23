package ru.otus.hw.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw.quiz.service.TestRunnerService;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        var springAppRun = SpringApplication.run(Application.class, args);
        var runnerServiceImp = springAppRun.getBean(TestRunnerService.class);
        runnerServiceImp.run();
    }
}
