package com.bank.service.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.bank.service.boot.controller", "com.bank.service.boot.repository" })
public class BankbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankbootApplication.class, args);
	}
}
