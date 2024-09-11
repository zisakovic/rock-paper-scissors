package com.acmegames.rockpaperscissors.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import com.acmegames.rockpaperscissors.dao.Game;
import com.acmegames.rockpaperscissors.dao.GameStatistics;
import com.acmegames.rockpaperscissors.enums.Move;
import com.acmegames.rockpaperscissors.enums.Result;
import com.acmegames.rockpaperscissors.repository.GameRepository;
import com.acmegames.rockpaperscissors.util.GameHelper;

class GameServiceImplTest {

	@InjectMocks
	private GameServiceImpl gameService;

	@Mock
	private GameRepository gameRepository;

	@Mock
	private GameHelper gameHelper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testNewGame() {
		Long playerId = 1L;
		Game game = new Game(playerId);
		game.setId(1L);
        when(gameRepository.save(any(Game.class))).thenReturn(game);
        
		Long result = gameService.newGame(playerId);
		
		assertEquals(1, result.longValue());
	}

	@ParameterizedTest
	@MethodSource("movesProvider")
	void testMakeMove(Move playerMove, Move opponentMove, String expectedResult) {
		try (MockedStatic<GameHelper> gameHelper = mockStatic(GameHelper.class)) {
			gameHelper.when(GameHelper::getRandomMove).thenReturn(opponentMove);
			String result = gameService.makeMove(1L, 1L, playerMove);
			assertEquals(expectedResult, result);
		}
	}
	
	static Stream<Arguments> movesProvider() {
		return Stream.of(arguments(Move.ROCK, Move.ROCK, Result.DRAW.name()),
				arguments(Move.PAPER, Move.PAPER, Result.DRAW.name()),
				arguments(Move.SCISSORS, Move.SCISSORS, Result.DRAW.name()),
				arguments(Move.ROCK, Move.SCISSORS, Result.WIN.name()),
				arguments(Move.SCISSORS, Move.PAPER, Result.WIN.name()),
				arguments(Move.PAPER, Move.ROCK, Result.WIN.name()),
				arguments(Move.ROCK, Move.PAPER, Result.LOSE.name()),
				arguments(Move.PAPER, Move.SCISSORS, Result.LOSE.name()),
				arguments(Move.SCISSORS, Move.ROCK, Result.LOSE.name()));
	}

	@Test
	void testTerminateGame() {
		String result = gameService.terminateGame(1L);
		assertEquals("Game 1 terminated.", result);
	}

	@Test
	void testGetGameStatistics() {
		GameStatistics gameStatistics = gameService.getGameStatistics();
		assertNotNull(gameStatistics);
	}

}
