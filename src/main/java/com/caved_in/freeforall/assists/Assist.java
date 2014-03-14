package com.caved_in.freeforall.assists;

import java.util.HashSet;
import java.util.Set;

public class Assist {
	private String attacked = "";
	private final Set<String> attackers = new HashSet<>();

	public Assist(String attackedPlayer) {
		this.attacked = attackedPlayer;
	}

	public Set<String> getAttackers() {
		return attackers;
	}

	public void addAttacker(String playerName) {
		this.attackers.add(playerName);
	}
}
