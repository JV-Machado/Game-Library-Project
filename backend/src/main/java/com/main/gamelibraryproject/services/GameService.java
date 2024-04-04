package com.main.gamelibraryproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.gamelibraryproject.entities.Game;
import com.main.gamelibraryproject.entities.GameConsole;
import com.main.gamelibraryproject.repositories.GameRepository;
import com.main.gamelibraryproject.services.exceptions.DuplicateResourceException;
import com.main.gamelibraryproject.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class GameService {

	@Autowired
	private GameRepository gameRepository;
	
	public List<Game> allGames(){
		return gameRepository.findAll();
	}
	
	public Game findById(Long id) {
		Optional<Game> game = gameRepository.findById(id);
		return game.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
	}
	
	@Transactional
	public Game insert(Game game) {
		if(gameRepository.existsBygameName(game.getGameName())) {
			throw new DuplicateResourceException("Resource already exists: " + game.getGameName());
		}
		Game entity = gameRepository.save(game);
		return entity;
	}
	
	@Transactional
	public Game update(Long id, Game game) {
		if(!gameRepository.existsById(id)) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
		
		Optional<Game> existingGameWithName = gameRepository.findBygameName(game.getGameName());
	    existingGameWithName.ifPresent(existingGame -> {
	        if (!existingGame.getId().equals(id)) {
	            throw new DuplicateResourceException("Game name already exists: " + game.getGameName());
	        }
	    });
	    
	    game.setId(id);
	    Game entity = gameRepository.save(game);
	    return entity;
	}
	
	@Transactional
	public void delete(Long id) {
		if(!gameRepository.existsById(id)) {
			throw new ResourceNotFoundException("Id not found: " + id);
		}
		gameRepository.deleteById(id);
	}
}
