package com.acmegames.rockpaperscissors.service;

import com.acmegames.rockpaperscissors.dao.GameStatistics;
import com.acmegames.rockpaperscissors.enums.Move;

public interface GameService {

    Long newGame(Long playerId);

    String makeMove(Long gameId, Long playerId, Move move);

    String terminateGame(Long gameId);

    GameStatistics getGameStatistics();

}
