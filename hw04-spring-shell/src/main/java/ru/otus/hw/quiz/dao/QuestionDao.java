package ru.otus.hw.quiz.dao;

import ru.otus.hw.quiz.domain.Question;

import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}
