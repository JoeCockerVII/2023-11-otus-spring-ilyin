package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.quiz.config.AppProperties;
import ru.otus.hw.quiz.service.LocalizedMessagesService;

@RequiredArgsConstructor
@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

    private final AppProperties properties;

    @Override
    public String getMessage(String code, Object[] args) {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(properties.getBaseName());
        messageSource.setDefaultEncoding(properties.getEncoding());
        return messageSource.getMessage(code, args, properties.getLocale());
    }

}
