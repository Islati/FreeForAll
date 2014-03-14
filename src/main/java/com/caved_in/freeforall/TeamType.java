package com.caved_in.freeforall;

import java.util.EnumSet;
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
public enum TeamType {
	TERRORIST("t", "terrorist"),
	COUNTER_TERRORIST("ct", "counterterrorist", "counter_terrorist", "c_t");

	private String[] teamInitials;
	private static Map<String, TeamType> teamMap = new HashMap<>();

	static {
		for (TeamType teamType : EnumSet.allOf(TeamType.class)) {
			for (String initial : teamType.teamInitials) {
				teamMap.put(initial, teamType);
			}
		}
	}

	TeamType(String... teamInitials) {
		this.teamInitials = teamInitials;
	}

	@Override
	public String toString() {
		return this.teamInitials[0];
	}

	public static TeamType getTeamByInitials(String teamInitials) {
		return teamMap.get(teamInitials.toLowerCase());
	}
}
