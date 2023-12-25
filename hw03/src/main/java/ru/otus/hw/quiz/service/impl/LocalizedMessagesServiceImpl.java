package ru.otus.hw.quiz.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw.quiz.config.LocaleConfig;
import ru.otus.hw.quiz.service.LocalizedMessagesService;

@RequiredArgsConstructor
@Service
public class LocalizedMessagesServiceImpl implements LocalizedMessagesService {

    private final LocaleConfig localeConfig;

    @Override
    public String getMessage(String code, Object[] args) {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(localeConfig.getBaseName());
        messageSource.setDefaultEncoding(localeConfig.getEncoding());
        return messageSource.getMessage(code, args, localeConfig.getLocale());
    }

}
