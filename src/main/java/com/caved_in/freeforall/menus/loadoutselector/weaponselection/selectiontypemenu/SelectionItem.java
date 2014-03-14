package com.caved_in.freeforall.menus.loadoutselector.weaponselection.selectiontypemenu;

import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.Game.LoadoutSlot;
import com.caved_in.freeforall.GameMessages;
import com.caved_in.freeforall.menus.loadoutselector.weaponselection.primary.PrimaryWeaponTypeMenu;
import com.caved_in.freeforall.menus.loadoutselector.weaponselection.secondary.SecondaryWeaponTypeMenu;
import com.caved_in.freeforall.menus.loadoutselector.weaponselection.tertiary.PerkSelectionMenu;
import me.xhawk87.PopupMenuAPI.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class SelectionItem extends MenuItem {
	private LoadoutSlot loadoutSlot;
	private int loadoutNumber = 0;

	public SelectionItem(String text, MaterialData icon, LoadoutSlot loadoutSlot, int loadoutNumber) {
		super(text, icon);
		this.loadoutSlot = loadoutSlot;
		this.loadoutNumber = loadoutNumber;
	}

	@Override
	public void onClick(Player player) {
		switch (this.loadoutSlot) {
			case Primary:
				getMenu().switchMenu(player, new PrimaryWeaponTypeMenu(loadoutNumber).getMenu());
				PlayerHandler.sendMessage(player, GameMessages.SELECT_PRIMARY_WEAPON_TYPE);
				break;
			case Secondary:
				getMenu().switchMenu(player, new SecondaryWeaponTypeMenu(loadoutNumber).getMenu());
				PlayerHandler.sendMessage(player, GameMessages.SELECT_SECONDARY_WEAPON_TYPE);
				break;
			case Tertiary:
				getMenu().switchMenu(player, new PerkSelectionMenu(loadoutNumber, player).getMenu());
				PlayerHandler.sendMessage(player, GameMessages.SELECT_ACTIVE_PERK);
				break;
			default:
				break;
		}
	}
}
