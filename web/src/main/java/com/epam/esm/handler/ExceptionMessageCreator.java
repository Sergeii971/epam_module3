package com.epam.esm.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

/**
 * The type ExceptionMessageCreator.
 *
 * @author Verbovskiy Sergei
 * @version 1.0
 */
@Component
public class ExceptionMessageCreator {
    private final MessageSource messageSource;

    @Autowired
    public ExceptionMessageCreator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Create exception message
     *
     * @param exceptionMessageKey the exception message key
     * @param locale the locale
     * @return the localized exception message
     */
    public String createMessage(String exceptionMessageKey, Locale locale) {
        return messageSource.getMessage(exceptionMessageKey, new Object[]{}, locale);
    }

    /**
     * Create exception message
     *
     * @param exceptionMessageKeys the exception message keys
     * @param locale the locale
     * @return the localized exception message
     */
    public String createMessage(List<String> exceptionMessageKeys, Locale locale) {
        StringBuilder builder = new StringBuilder();

        for (String exceptionMessageKey : exceptionMessageKeys) {
            String errorMessage = messageSource.getMessage(exceptionMessageKey, new Object[]{}, locale);
            builder
                    .append(errorMessage)
                    .append(" ");
        }
        return builder.toString();
    }
}
