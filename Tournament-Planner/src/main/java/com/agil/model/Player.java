package com.agil.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Markus
 * 
 *
 */

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	private String name;

	@OneToOne(mappedBy = "connectedPlayer")
	private Member member;

	@ManyToMany
	@JoinTable(name = "player_team", joinColumns = @JoinColumn(name = "player_id"), inverseJoinColumns = @JoinColumn(name = "team_id"))
	private Set<Team> teams = new HashSet<>();

	public Player() {
		teams = new HashSet<>();
	}

	public Player(String name) {
		this.name = name;
	}
	
	public Player(String name, Team team) {
		this(name);
		addTeam(team);
	}
	
	public boolean addTeam(Team team) {
		return teams.add(team);
	}

	public boolean removeTeam(Team team) {
		return teams.remove(team);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
