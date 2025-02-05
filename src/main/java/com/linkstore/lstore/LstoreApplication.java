package com.linkstore.lstore;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LstoreApplication.class, args);
	}

	// CommandLineRunner is to execute task during the startup phase of application
	// https://javadzone.com/spring-boot-runners-commandline-vs-application/
	// TODO: Remove below annotation to use this
	// @Bean
	public CommandLineRunner lstoreCommandLineRunner(ApplicationContext ctx) {
		return (arguments) -> {
			System.out.println("lstoreCommandLineRunner...");
			/*String[] beanNames = ctx.getBeanDefinitionNames();
			for(String beanName : beanNames) {
				System.out.println(beanName);
			}*/
		};
	}

	// ApplicationRunner is to execute task during the startup phase of application
	// https://javadzone.com/spring-boot-runners-commandline-vs-application/
	// TODO: Remove below annotation to use this
	// @Bean
	public ApplicationRunner lstoreApplicationRunner(ApplicationContext ctx) {
		return (arguments) -> {
			System.out.println("lstoreApplicationRunner...");
			/*String[] beanNames = ctx.getBeanDefinitionNames();
			for(String beanName : beanNames) {
				System.out.println(beanName);
			}*/
		};
	}
}
