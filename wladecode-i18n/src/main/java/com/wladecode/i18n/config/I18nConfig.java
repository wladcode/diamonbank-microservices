package com.wladecode.i18n.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class I18nConfig {

    private final Environment env;

    public I18nConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public MessageSource messageSource() {
        // "i18n" here assumes your keys in the properties file are like i18n.greeting.hello
        // or if you used a base name in the config server like 'i18n'.
        return new EnvironmentMessageSource(env, "i18n");
    }
}