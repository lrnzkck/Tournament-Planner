package com.agil.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.agil.model.Game;

public interface GameService {

	public List<Game> getAll();
	
	public Optional<Game> findOne(String name);
	
	Optional<Game> findOne(Long id);
	
	List<Game> findByNameIgnoreCase(String name);
	
	List<Game> findByNameIgnoreCaseContaining(String name);
	
	void save(@Valid Game game);

	public List<Game> findByType(String type);
	
	public List<Game> findByTeamA_Players_Name(String name);
	
	public List<Game> findByTeamB_Players_Name(String name);
	
	public void delete(Game game);
}
