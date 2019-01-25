package br.com.eduardosatyra.cervejaria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiCervejariaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApiCervejariaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
