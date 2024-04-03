package com.main.gamelibraryproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.gamelibraryproject.entities.GameConsole;
import com.main.gamelibraryproject.repositories.GameConsoleRepository;
import com.main.gamelibraryproject.services.exceptions.DuplicateResourceException;
import com.main.gamelibraryproject.services.exceptions.ResourceNotFoundException;

import jakarta.transaction.Transactional;

@Service
public class GameConsoleService {

	@Autowired
	private GameConsoleRepository gameConsoleRepository;
	
	public List<GameConsole> allConsoles(){
		return gameConsoleRepository.findAll();
	}
	
	public GameConsole findConsoleById(Long id) {
		Optional<GameConsole> console = gameConsoleRepository.findById(id);
		return console.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
	}
	
	@Transactional
	public GameConsole insert(GameConsole console) {
		if(gameConsoleRepository.existsByconsoleName(console.getConsoleName())) {
			throw new DuplicateResourceException("Este console já existe");
		}
		GameConsole entity = gameConsoleRepository.save(console);
		return entity;
	}
	
	@Transactional
	public GameConsole update(Long id, GameConsole console) {
		if(!gameConsoleRepository.existsById(id)) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		
		Optional<GameConsole> existingConsoleWithName = gameConsoleRepository.findByconsoleName(console.getConsoleName());
	    existingConsoleWithName.ifPresent(existingConsole -> {
	        if (!existingConsole.getId().equals(id)) {
	            throw new DuplicateResourceException("Console name already exists: " + console.getConsoleName());
	        }
	    });
		
		console.setId(id);
		GameConsole entity = gameConsoleRepository.save(console);
		return entity;
	}
	
	@Transactional
	public void delete(Long id) {
		if(!gameConsoleRepository.existsById(id)) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		gameConsoleRepository.deleteById(id);
	}
	
	
}
