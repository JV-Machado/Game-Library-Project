package com.main.gamelibraryproject.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "tb_game")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="game_name")
	private String gameName;
	
	@Column(name="game_genre")
	private String gameGenre;
	
	@Column(name="release_year")
	private Integer releaseYear;
	
	@Column(name="units_sold")
	private Integer unitsSold;
	
	@ManyToOne
	@JoinColumn(name="game_console_id")
	private GameConsole gameConsole;
	
}
