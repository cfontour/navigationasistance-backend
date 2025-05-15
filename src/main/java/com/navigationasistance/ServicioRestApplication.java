package com.navigationasistance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.navigationasistance")
public class ServicioRestApplication {

	public static void main(String[] args) {
		System.out.println(">>> INICIANDO APLICACIÓN SPRING");
		SpringApplication.run(ServicioRestApplication.class, args);
	}

}
