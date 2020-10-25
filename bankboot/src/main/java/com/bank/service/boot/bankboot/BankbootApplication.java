package com.bank.service.boot.bankboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bank.service.boot.bankboot.controller", "com.bank.service.boot.bankboot.repository" })
public class BankbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankbootApplication.class, args);
	}

}
