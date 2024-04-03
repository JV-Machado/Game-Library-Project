package com.main.gamelibraryproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.gamelibraryproject.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
