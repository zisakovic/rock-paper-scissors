package com.acmegames.rockpaperscissors.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acmegames.rockpaperscissors.dao.GameStatistics;
import com.acmegames.rockpaperscissors.enums.Move;
import com.acmegames.rockpaperscissors.service.GameService;

@RestController
@RequestMapping("/api/games/rps")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
		
	@PostMapping("/new")
    public ResponseEntity<Long> newGame(@RequestParam Long playerId) {
        Long gameId = gameService.newGame(playerId);
        return ResponseEntity.ok(gameId);
    }
	
	@PostMapping("/move")
    public ResponseEntity<String> makeMove(
    		@RequestParam("gameId") Long gameId,
    		@RequestParam("playerId") Long playerId,
            @RequestParam("move") Move move) {
        String result = gameService.makeMove(gameId, playerId, move);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/terminate")
    public ResponseEntity<String> terminateGame(@RequestParam Long gameId) {
        String response = gameService.terminateGame(gameId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<GameStatistics> getStatistics() {
        GameStatistics statistics = gameService.getGameStatistics();
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

}
