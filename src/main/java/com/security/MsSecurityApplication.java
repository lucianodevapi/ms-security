package com.security;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class MsSecurityApplication {


	public static void main(String[] args) {
		SpringApplication.run(MsSecurityApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner createPasswordCommand(PasswordEncoder password){
//		return args -> {
//			System.out.println(password.encode("12345678"));
//		};
//	}

}
