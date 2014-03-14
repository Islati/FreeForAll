package com.caved_in.freeforall.menus.loadoutselector;

import com.caved_in.freeforall.events.CustomEventHandler;
import com.caved_in.freeforall.events.LoadoutSelectEvent;
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
public class LoadoutSelectionItem extends MenuItem {
	private int selectedLoadout = 0;

	public LoadoutSelectionItem(String text, MaterialData icon, int loadoutNumber) {
		super(text, icon);
		this.selectedLoadout = loadoutNumber;
	}

	@Override
	public void onClick(Player player) {
		//Create a loadout selection event and call it, then close this menu
		LoadoutSelectEvent loadoutSelectEvent = new LoadoutSelectEvent(player, selectedLoadout);
		CustomEventHandler.handleLoadoutSelectEvent(loadoutSelectEvent);
		getMenu().closeMenu(player);
	}

}
