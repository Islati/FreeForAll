package com.caved_in.freeforall.fakeboard;

import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.TeamType;
import com.google.common.collect.Sets;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class FakeboardHandler {
	private static final Map<TeamType, Team> activeTeams = new HashMap<>();
	private static final Map<String, GamePlayer> activePlayers = new HashMap<>();

	public static void registerTeam(TeamType team) {
		Team newTeam = new Team(team);
		newTeam.setFriendlyFire(false);
		activeTeams.put(team, newTeam);
	}

	public static boolean loadPlayer(String playerName) {
		try {
			GamePlayer gamePlayer = GamePlayer.initPlayer(playerName);
			activePlayers.put(playerName, gamePlayer);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}


	public static GamePlayer getPlayer(String playerName) {
		return activePlayers.get(playerName);
	}

	public static GamePlayer getPlayer(Player player) {
		return getPlayer(player.getName());
	}

	//Todo: Optimize this method, and clean this up
	public static void cleanTeams() {
		for (Entry<TeamType, Team> teamEntry : activeTeams.entrySet()) {
			for (Player player : getPlayers(teamEntry.getKey())) {
				getPlayer(player).setTeam(null);
				resetScores(player);
				removeFromTeam(teamEntry.getKey(), player);
				PlayerHandler.clearInventory(player);
				PlayerHandler.removePotionEffects(player);
			}
		}
		activeTeams.clear();
	}

	public static void resetScores(Player player) {
		GamePlayer GamePlayer = getPlayer(player);
		GamePlayer.setPlayerScore(0);
		GamePlayer.resetDeaths();
		GamePlayer.resetKillstreak();
	}

	public static TeamType getPlayerTeam(Player player) {
		return getPlayer(player).getTeam();
	}

	public static Team getTeamByPlayer(String playerName) {
		GamePlayer player = getPlayer(playerName);
		if (player != null) {
			return getTeam(player.getTeam());
		}
		return null;
	}

	public static Team getTeamByPlayer(Player player) {
		return getTeamByPlayer(player.getName());
	}

	public static boolean removeFromTeam(TeamType team, Player player) {
		return activeTeams.get(team).removePlayer(player);
	}

	public static boolean addToTeam(TeamType team, GamePlayer player) {
		Team addingTeam = activeTeams.get(team);
		if (addingTeam != null && !addingTeam.hasPlayer(player)) {
			addingTeam.addPlayer(player);
			return true;
		}
		return false;
	}

	public static void setFriendlyFire(TeamType team, boolean friendlyFire) {
		activeTeams.get(team).setFriendlyFire(friendlyFire);
	}

	public static Set<Player> getPlayers(TeamType team) {
		Set<Player> players = new HashSet<>();
		for (GamePlayer teamPlayer : activeTeams.get(team).getTeamMembers()) {
			Player player = teamPlayer.getPlayer();
			if (player != null) {
				players.add(player);
			}
		}
		return players;
	}

	public static Set<GamePlayer> getOnlineGameplayers() {
		return Sets.newHashSet(activePlayers.values());
	}

	public static Team getTeam(TeamType team) {
		return activeTeams.get(team);
	}

	public static void removePlayer(Player player) {
		activePlayers.remove(player.getName());
	}

}
