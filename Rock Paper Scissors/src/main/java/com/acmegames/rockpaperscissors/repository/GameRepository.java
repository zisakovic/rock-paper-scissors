package com.acmegames.rockpaperscissors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acmegames.rockpaperscissors.dao.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

}