package com.acmegames.rockpaperscissors.util;

import java.util.random.RandomGenerator;

import com.acmegames.rockpaperscissors.enums.Move;

public class GameHelper {

    private static final RandomGenerator RANDOM = RandomGenerator.getDefault();

	public static Move getRandomMove() {
        return Move.values()[RANDOM.nextInt(Move.values().length)];
    }
}
