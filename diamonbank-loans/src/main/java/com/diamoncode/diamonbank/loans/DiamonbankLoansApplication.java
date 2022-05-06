package com.diamoncode.diamonbank.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@RefreshScope
public class DiamonbankLoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiamonbankLoansApplication.class, args);
	}

}
