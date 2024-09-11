package com.acmegames.rockpaperscissors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class RockPaperScissorsApplicationTests {

	@Test
	void contextLoads() {
	}

}
