package com.backend.challengeforohub;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChallengeforohubApplication {

	private final static Logger LOGGER = LoggerFactory.getLogger(ChallengeforohubApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ChallengeforohubApplication.class, args);

		LOGGER.info("Proyecto Dev  ¡¡¡¡¡¡¡¡¡¡INITIALIZED!!!!!!!!!!!... port(s): 8080");

	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
