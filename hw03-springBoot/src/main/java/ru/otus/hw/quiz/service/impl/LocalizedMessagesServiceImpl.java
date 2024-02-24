package ru.otus.hw.quiz.service.impl;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.quiz.config.LocaleConfig;
import ru.otus.hw.quiz.service.LocalizedMessagesService;

@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

    private final LocaleConfig localeConfig;

    private final ReloadableResourceBundleMessageSource messageSource;

    public LocalizedMessagesServiceImpl(LocaleConfig localeConfig) {
        this.localeConfig = localeConfig;
        this.messageSource = new ReloadableResourceBundleMessageSource();
    }

    @Override
    public String getMessage(String code, Object... args) {
        messageSource.setBasename(localeConfig.getBaseName());
        messageSource.setDefaultEncoding(localeConfig.getEncoding());
        return messageSource.getMessage(code, args, localeConfig.getLocale());
    }

}
