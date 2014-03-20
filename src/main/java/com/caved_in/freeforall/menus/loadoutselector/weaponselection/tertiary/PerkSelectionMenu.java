package com.caved_in.freeforall.menus.loadoutselector.weaponselection.tertiary;

import com.caved_in.commons.menu.Menus;
import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class PerkSelectionMenu {
	private PopupMenu perkMenu;

	public PerkSelectionMenu(int loadoutSlot, Player player) {
		List<PerkMenuItem> perkMenuItems = PerkRender.renderPerks(loadoutSlot, player);
		perkMenu = PopupMenuAPI.createMenu("Select a perk!", Menus.getRows(perkMenuItems.size()));
		for (int I = 0; I < perkMenuItems.size(); I++) {
			perkMenu.addMenuItem(perkMenuItems.get(I), I);
		}
		perkMenu.setExitOnClickOutside(false);
	}

	public PopupMenu getMenu() {
		return perkMenu;
	}
}
