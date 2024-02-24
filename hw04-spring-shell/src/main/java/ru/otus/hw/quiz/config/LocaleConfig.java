package ru.otus.hw.quiz.config;

import java.util.Locale;

public interface LocaleConfig {
    Locale getLocale();

    String getEncoding();

    String getBaseName();
}
