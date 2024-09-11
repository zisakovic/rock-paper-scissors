package com.acmegames.rockpaperscissors;

import org.springframework.boot.SpringApplication;

public class TestRockPaperScissorsApplication {

	public static void main(String[] args) {
		SpringApplication.from(RockPaperScissorsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
