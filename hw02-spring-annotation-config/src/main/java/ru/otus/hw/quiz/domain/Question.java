package ru.otus.hw.quiz.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}
