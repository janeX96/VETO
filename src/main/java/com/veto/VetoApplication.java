package com.veto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class VetoApplication implements RepositoryRestConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(VetoApplication.class, args);
	}
	@Bean
	Validator validator() {
		return new LocalValidatorFactoryBean();
	}
}
