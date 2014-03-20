package com.caved_in.freeforall.fakeboard;

import com.caved_in.commons.player.Players;
import com.google.common.collect.Sets;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static ch.lambdaj.Lambda.maxFrom;
/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class FakeboardHandler {
	private static final Map<String, GamePlayer> activePlayers = new HashMap<>();

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

	public static void resetScores(Player player) {
		GamePlayer GamePlayer = getPlayer(player);
		GamePlayer.setPlayerScore(0);
		GamePlayer.resetDeaths();
		GamePlayer.resetKillstreak();
	}

	public static Set<GamePlayer> getGamePlayers() {
		return Sets.newHashSet(activePlayers.values());
	}

	public static Set<GamePlayer> getGamePlayersExcept(String... exceptions) {
		Set<GamePlayer> gamePlayers = new HashSet<>();
		Set<String> names = Sets.newHashSet(exceptions);
		for (Map.Entry<String, GamePlayer> entry : activePlayers.entrySet()) {
			if (!names.contains(entry.getKey())) {
				gamePlayers.add(entry.getValue());
			}
		}
		return gamePlayers;
	}

	public static void removePlayer(Player player) {
		activePlayers.remove(player.getName());
	}

	public static int getHighestScore() {
		return maxFrom(activePlayers.values()).getPlayerScore();
	}

	public static GamePlayer getTopPlayer() {
		int highestScore = getHighestScore();
		for(GamePlayer gamePlayer : activePlayers.values()) {
			if (highestScore == gamePlayer.getPlayerScore()) {
				return gamePlayer;
			}
		}
		throw new RuntimeException("Not able to get highest scoring player, as there are no players on");
	}

	public static void resetPlayerData() {
		for(GamePlayer gamePlayer : activePlayers.values()) {
			gamePlayer.resetDeaths();
			gamePlayer.resetKillstreak();
			gamePlayer.setPlayerScore(0);
			Players.clearInventory(gamePlayer.getPlayer(), true);
		}
	}
}
