package ru.otus.hw.quiz.service;

public interface LocalizedMessagesService {
    String getMessage(String code, Object ...args);
}
