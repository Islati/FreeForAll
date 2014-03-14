package com.caved_in.freeforall.perks;

import java.util.Collection;
import java.util.HashMap;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class PerkHandler {
	private static HashMap<String, Perk> perks = new HashMap<>();

	public static void initializePerks(Perk... perks) {
		for (Perk perk : perks) {
			initializePerk(perk);
		}
	}

	public static void initializePerk(Perk perk) {
		perks.put(perk.getPerkName().toLowerCase(), perk);
	}

	public static Collection<Perk> getPerks() {
		return perks.values();
	}

	public static Perk getPerk(String perkName) {
		return perks.get(perkName.toLowerCase());
	}

}
