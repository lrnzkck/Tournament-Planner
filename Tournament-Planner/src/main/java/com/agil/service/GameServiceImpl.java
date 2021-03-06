package com.agil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agil.model.Game;
import com.agil.repo.GameRepository;
import com.agil.utility.GameType;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository repo;

	
	@Override
	public List<Game> getAll() {
		return StreamSupport.stream(repo.findAll().spliterator(),  false).collect(Collectors.toList());
	}
	
	@Override
	public Optional<Game> findOne(Long id){
		if(id != null)
			return repo.findById(id);
		return Optional.empty();
	}
	
	@Override
	public Optional<Game> findOne(String name) {
		return repo.findByName(name);
	}
	
	@Override
	public List<Game> findByNameIgnoreCase(String name){
		return repo.findByNameIgnoreCase(name);
	}
	
	@Override
	public List<Game> findByNameIgnoreCaseContaining(String name){
		return repo.findByNameIgnoreCaseContaining(name);
	}
	
	@Override
	public void save(@Valid Game game) {
		repo.save(game);
	}

	@Override
	public List<Game> findByType(String type) {
		return repo.findByType(GameType.fromLowerCase(type.toUpperCase()));
	}

	@Override
	public List<Game> findByTeamA_Players_Name(String name) {
		return repo.findByTeamA_Players_Name(name);
	}

	@Override
	public List<Game> findByTeamB_Players_Name(String name) {
		return repo.findByTeamB_Players_Name(name);
	}

	@Override
	public void delete(Game game) {
		this.repo.delete(game);
	}
	
	
}
