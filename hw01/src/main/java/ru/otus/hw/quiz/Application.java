package ru.otus.hw.quiz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.hw.quiz.service.TestRunnerService;

public class Application {
    public static void main(String[] args) {

        //Прописать бины в spring-context.xml и создать контекст на его основе
        var context = new ClassPathXmlApplicationContext("spring-context.xml");
        var testRunnerService = context.getBean(TestRunnerService.class);
        testRunnerService.run();

    }
}