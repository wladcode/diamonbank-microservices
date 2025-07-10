package com.wladecode.i18n.config;

import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;

// This is a simplified example. A full implementation would handle caching, fallback, etc.
public class EnvironmentMessageSource extends AbstractMessageSource {

    private final Environment environment;
    private final String baseName; // e.g., "messages" or "i18n" for prefixing

    public EnvironmentMessageSource(Environment environment, String baseName) {
        this.environment = environment;
        this.baseName = baseName;
    }

    @Override
    @Nullable
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String localeSpecificKey = buildKey(code, locale);
        String message = environment.getProperty(localeSpecificKey);
        if (message == null) {
            // Fallback to default locale (no locale suffix)
            String defaultKey = buildKey(code, null);
            message = environment.getProperty(defaultKey);
        }
        return message;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = resolveCodeWithoutArguments(code, locale);
        return (msg != null ? createMessageFormat(msg, locale) : null);
    }

    private String buildKey(String code, @Nullable Locale locale) {
        StringBuilder keyBuilder = new StringBuilder(baseName);
        keyBuilder.append(".").append(code); // e.g., i18n.greeting.hello
        if (locale != null && !locale.equals(Locale.getDefault())) { // Only append if not default locale
            keyBuilder.append(".").append(locale.getLanguage()); // e.g., i18n.greeting.hello.es
        }
        return keyBuilder.toString();
    }
}