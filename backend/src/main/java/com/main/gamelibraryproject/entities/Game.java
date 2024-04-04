package com.main.gamelibraryproject.entities;

import org.hibernate.validator.constraints.NotEmpty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank
	private String gameName;
	
	@Column(name="game_genre")
	@NotBlank
	private String gameGenre;
	
	@Column(name="release_year")
	@NotNull
	private Integer releaseYear;
	
	@Column(name="units_sold")
	@NotNull
	private Integer unitsSold;
	
	@ManyToOne
	@JoinColumn(name="game_console_id")
	@NotNull
	private GameConsole gameConsole;
	
}
