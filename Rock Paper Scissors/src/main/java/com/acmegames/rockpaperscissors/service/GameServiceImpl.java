package com.acmegames.rockpaperscissors.service;

import static com.acmegames.rockpaperscissors.enums.Move.ROCK;
import static com.acmegames.rockpaperscissors.enums.Move.PAPER;
import static com.acmegames.rockpaperscissors.enums.Move.SCISSORS;

import com.acmegames.rockpaperscissors.dao.Game;
import com.acmegames.rockpaperscissors.dao.GameStatistics;
import com.acmegames.rockpaperscissors.enums.Move;
import com.acmegames.rockpaperscissors.enums.Result;
import com.acmegames.rockpaperscissors.repository.GameRepository;
import com.acmegames.rockpaperscissors.util.GameHelper;

public class GameServiceImpl implements GameService {
    
	private final GameRepository gameRepository;
	 
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
	    
	@Override
	public Long newGame(Long playerId) {
        Game game = new Game(playerId);
        return gameRepository.save(game).getId();
	}

	@Override
	public String makeMove(Long gameId, Long playerId, Move move) {
		Move opponentMove = GameHelper.getRandomMove();
		if (move == opponentMove) {
			return Result.DRAW.name();
		}
		if ((move == ROCK && opponentMove == SCISSORS)
				|| (move == SCISSORS && opponentMove == PAPER)
				|| (move == PAPER && opponentMove == ROCK)) {
			return Result.WIN.name();
		}
        return Result.LOSE.name();
	}

	@Override
	public String terminateGame(Long gameId) {
		return "Game " + gameId + " terminated.";
	}

	@Override
	public GameStatistics getGameStatistics() {
		return new GameStatistics();
	}

    

}
