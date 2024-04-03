package com.main.gamelibraryproject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.gamelibraryproject.entities.GameConsole;

public interface GameConsoleRepository extends JpaRepository<GameConsole, Long>{

	boolean existsByconsoleName(String name);
	boolean existsById(Long id);
	Optional<GameConsole> findByconsoleName(String name);
}
