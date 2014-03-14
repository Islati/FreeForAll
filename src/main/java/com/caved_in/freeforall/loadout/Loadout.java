package com.caved_in.freeforall.loadout;

import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.perks.Perk;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class Loadout {
	private String primary = "AK-47";
	private String secondary = "USP45";

	private int loadoutNumber = 0;

	private Perk perk;
	private String playerName = "";

	public Loadout(String playerName, int loadoutNumber, String primaryWeapon, String secondaryWeapon, Perk loadoutPerk) {
		this.playerName = playerName;
		this.loadoutNumber = loadoutNumber;
		this.primary = primaryWeapon;
		this.secondary = secondaryWeapon;
		this.perk = loadoutPerk;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public int getNumber() {
		return this.loadoutNumber;
	}

	public String getPrimary() {
		return this.primary;
	}

	public String getSecondary() {
		return this.secondary;
	}

	public Perk getPerk() {
		return this.perk;
	}

	public void setPrimary(String ID) {
		this.primary = ID;
		updateSql();
	}

	public void setSecondary(String id) {
		this.secondary = id;
		updateSql();
	}

	public void setPerk(Perk perk) {
		this.perk = perk;
		updateSql();
	}

	public void updateSql() {
		Game.runnableManager.runTaskAsynch(new Runnable() {
			@Override
			public void run() {
				Game.loadoutSQL.updateLoadout(playerName, loadoutNumber, primary, secondary, perk.getPerkName());
			}
		});
	}
}
