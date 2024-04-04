package com.main.gamelibraryproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.gamelibraryproject.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

	boolean existsBygameName(String name);
	boolean existsById(Long id);
	Optional<Game> findBygameName(String name);
}
