package com.caved_in.freeforall.menus.loadoutselector;

import com.caved_in.commons.item.Items;
import com.caved_in.commons.menu.Menus;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.gamehandler.GameSetupHandler;
import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

import java.util.Arrays;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class LoadoutSelectionMenu {
	private PopupMenu sLoadout;

	private LoadoutSelectionMenu(String playerName) {
		GamePlayer gamePlayer = FakeboardHandler.getPlayer(playerName);
		gamePlayer.setAfk(true, false);
		int loadoutLimit = gamePlayer.getLoadoutLimit();
		sLoadout = PopupMenuAPI.createMenu("Select a Loadout", Menus.getRows(loadoutLimit));
		for (int I = 0; I < loadoutLimit; I++) {
			int loadoutNumber = I + 1;
			LoadoutSelectionItem loadoutItem = new LoadoutSelectionItem("Loadout #" + loadoutNumber, new MaterialData(Material.CHEST), loadoutNumber);
			loadoutItem.setDescriptions(Arrays.asList(Items.getName(Game.crackShotAPI.generateWeapon(gamePlayer.getPrimaryGunID(loadoutNumber))),
					Items.getName(Game.crackShotAPI.generateWeapon(gamePlayer.getSecondaryGunID(loadoutNumber)))));
			sLoadout.addMenuItem(loadoutItem, I);
		}
		sLoadout.setExitOnClickOutside(false);
	}

	public LoadoutSelectionMenu(Player player) {
		this(player.getName());
		GameSetupHandler.givePlayerLoadoutGem(player);
		getMenu().openMenu(player);
	}

	public PopupMenu getMenu() {
		return sLoadout;
	}
}
