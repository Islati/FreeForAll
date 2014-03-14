package com.caved_in.freeforall.menus.loadoutselector;

import me.xhawk87.PopupMenuAPI.PopupMenu;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class LoadoutActionMenu extends PopupMenu {

	public LoadoutActionMenu(Player player) {
		super("Select & Edit Loadouts", 1);
		addMenuItem(new LoadoutItem(LoadoutItem.LoadoutAction.SELECT), 0);
		addMenuItem(new LoadoutItem(LoadoutItem.LoadoutAction.EDIT), 1);
		openMenu(player);

	}
}
