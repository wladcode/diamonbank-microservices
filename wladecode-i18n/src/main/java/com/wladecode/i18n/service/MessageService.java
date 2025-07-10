package com.wladecode.i18n.service;

import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.context.MessageSource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    private final Environment environment;

    public MessageService(Environment environment, MessageSource messageSource1) {
        this.environment = environment;
    }

    public Map<String, String> loadI18nProperties(String language) {
        Map<String, String> i18nMessages = new HashMap<>();
        String localPrefix = language.concat(".") ;

        if (environment instanceof AbstractEnvironment) {
            StreamSupport.stream(((AbstractEnvironment) environment).getPropertySources().spliterator(), false)
                    .filter(ps -> ps instanceof OriginTrackedMapPropertySource && ps.getName().contains("i18n-".concat(language)))
                    .map(ps -> (EnumerablePropertySource<?>) ps)
                    .forEach(ps -> {
                        for (String key : ps.getPropertyNames()) {
                            // Only include keys that start with the specific locale prefix
                            //if (key.startsWith(localPrefix)) {
                                String value = environment.getProperty(key);
                                if (value != null) {
                                    i18nMessages.put(key, value);
                                }
                            //}
                        }
                    });
        }


        return i18nMessages;

    }

}
