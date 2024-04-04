package com.main.gamelibraryproject.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.main.gamelibraryproject.entities.Game;
import com.main.gamelibraryproject.services.GameService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/game")
public class GameController {
	
	@Autowired
	private GameService gameService;
	
	@GetMapping
	public ResponseEntity<List<Game>> listAllGames(){
		List<Game> list = gameService.allGames();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Game> findById(@PathVariable Long id){
		Game game = gameService.findById(id);
		return ResponseEntity.ok().body(game);
	}
	
	@PostMapping
	public ResponseEntity<Game> insert(@Valid @RequestBody Game game){
		Game entity = gameService.insert(game);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Game> update(@PathVariable Long id, @Valid @RequestBody Game game){
		Game entity = gameService.update(id, game);
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		gameService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
