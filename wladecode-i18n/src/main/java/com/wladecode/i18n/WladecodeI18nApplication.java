package com.wladecode.i18n;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class WladecodeI18nApplication {

	public static void main(String[] args) {
		SpringApplication.run(WladecodeI18nApplication.class, args);
	}

}
