package com.acmegames.rockpaperscissors.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.acmegames.rockpaperscissors.dao.GameStatistics;
import com.acmegames.rockpaperscissors.enums.Move;
import com.acmegames.rockpaperscissors.enums.Result;
import com.acmegames.rockpaperscissors.service.GameService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class GameControllerIntegrationTest {
    
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;
    
    @InjectMocks
    private GameController gameController;
    
	@Test
	void testNewGame() throws Exception {
		
        when(gameService.newGame(1L)).thenReturn(1L);
        
        mockMvc.perform(post("/api/games/rps/new")
                .param("playerId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
	}

	@Test
	void testMakeMove() throws Exception {

        when(gameService.makeMove(1L, 1L, Move.ROCK)).thenReturn(Result.WIN.name());

        mockMvc.perform(post("/api/games/rps/move")
        		.param("gameId", "1")
        		.param("playerId", "1")
        		.param("move", "ROCK"))
                .andExpect(status().isOk())
                .andExpect(content().string(Result.WIN.name()));
	}

	@Test
	void testTerminateGame() throws Exception {
		
		when(gameService.terminateGame(1L)).thenReturn("Game 1 terminated.");
		
        mockMvc.perform(post("/api/games/rps/terminate")
                .param("gameId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Game 1 terminated."));
	}

	@Test
	void testGetStatistics() throws Exception {
		
		GameStatistics stats = new GameStatistics();
		stats.setId(1L);
		
		when(gameService.getGameStatistics()).thenReturn(stats);

        mockMvc.perform(get("/api/games/rps/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
	}

}
