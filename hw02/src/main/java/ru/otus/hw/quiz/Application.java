package ru.otus.hw.quiz;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.otus.hw.quiz.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {

        //Создать контекст на основе Annotation/Java конфигурирования //todo
        var context = new AnnotationConfigApplicationContext();
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}