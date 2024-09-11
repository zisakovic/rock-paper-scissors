package com.acmegames.rockpaperscissors.dao;

import com.acmegames.rockpaperscissors.enums.Move;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long playerId;

    @Enumerated(EnumType.STRING)
    private Move playerMove;

    @Enumerated(EnumType.STRING)
    private Move opponentMove;

    public Game(Long playerId) {
    	this.playerId = playerId;
	}
}