package com.caved_in.freeforall.fakeboard;

import com.caved_in.freeforall.TeamType;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class Team {
	private TeamType team;

	private Map<String, GamePlayer> teamMembers = new HashMap<>();
	private Boolean allowFriendlyFire = false;
	private int teamScore = 0;

	public Team(TeamType teamType) {
		this.team = teamType;
	}

	@Override
	public String toString() {
		return team.toString();
	}

	public TeamType getType() {
		return team;
	}

	public boolean hasPlayer(String playerName) {
		return teamMembers.containsKey(playerName);
	}

	public boolean hasPlayer(Player player) {
		return hasPlayer(player.getName());
	}

	public boolean hasPlayer(GamePlayer player) {
		return hasPlayer(player.getName());
	}

	public boolean removePlayer(String playerName) {
		return teamMembers.remove(playerName) != null;
	}

	public boolean removePlayer(Player player) {
		return removePlayer(player.getName());
	}

	public void addPlayer(String playerName) {
		teamMembers.put(playerName, FakeboardHandler.getPlayer(playerName));
	}

	public void addPlayer(Player player) {
		addPlayer(player.getName());
	}

	public void addPlayer(GamePlayer player) {
		player.setTeam(team);
		teamMembers.put(player.getName(), player);
	}

	public GamePlayer getPlayer(String playerName) {
		return teamMembers.get(playerName);
	}

	public GamePlayer getPlayer(Player player) {
		return getPlayer(player.getName());
	}

	public Collection<GamePlayer> getTeamMembers() {
		return teamMembers.values();
	}

	public int getTeamSize() {
		return teamMembers.size();
	}

	public void setScore(String playerName, int score) {
		getPlayer(playerName).setPlayerScore(score);
	}

	public void addScore(String playerName, int incrementAmount) {
		getPlayer(playerName).addScore(incrementAmount);
	}

	public void addTeamScore(int amount) {
		this.teamScore += amount;
	}

	public int getTeamScore() {
		return teamScore;
	}

	public boolean allowFriendlyFire() {
		return allowFriendlyFire;
	}

	public void setFriendlyFire(boolean friendlyFire) {
		this.allowFriendlyFire = friendlyFire;
	}
}
