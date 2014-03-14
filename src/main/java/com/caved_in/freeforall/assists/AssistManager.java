package com.caved_in.freeforall.assists;

import java.util.HashMap;
import java.util.Map;

public class AssistManager {
	private static Map<String, Assist> playerAssists = new HashMap<>();

	public static boolean hasData(String playerName) {
		return playerAssists.containsKey(playerName);
	}

	public static void removeData(String playerName) {
		playerAssists.remove(playerName);
	}

	public static Assist getData(String playerName) {
		return playerAssists.get(playerName);
	}

	public static void addData(String attackedPlayer, String attackingPlayer) {
		Assist assist = (hasData(attackedPlayer) ? getData(attackedPlayer) : new Assist(attackedPlayer));
		assist.addAttacker(attackingPlayer);
		playerAssists.put(attackedPlayer, assist);
	}

}
