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

import com.main.gamelibraryproject.entities.GameConsole;
import com.main.gamelibraryproject.services.GameConsoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/gameconsole")
public class GameConsoleController {
	
	@Autowired
	private GameConsoleService gameConsoleService;
	
	@GetMapping
	public ResponseEntity<List<GameConsole>> allConsoles(){
		List<GameConsole> list = gameConsoleService.allConsoles();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<GameConsole> findById(@PathVariable Long id){
		GameConsole console = gameConsoleService.findConsoleById(id);
		return ResponseEntity.ok().body(console);
	}
	
	@PostMapping
	public ResponseEntity<GameConsole> insert(@Valid @RequestBody GameConsole console){
		GameConsole entity = gameConsoleService.insert(console);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(entity.getId()).toUri();
		return ResponseEntity.created(uri).body(entity);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<GameConsole> update(@PathVariable Long id, @Valid @RequestBody GameConsole console) {
		GameConsole entity = gameConsoleService.update(id, console);
		return ResponseEntity.ok().body(entity);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		gameConsoleService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
