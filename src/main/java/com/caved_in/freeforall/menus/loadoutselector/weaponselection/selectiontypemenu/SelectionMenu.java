package com.caved_in.freeforall.menus.loadoutselector.weaponselection.selectiontypemenu;

import com.caved_in.freeforall.Game;
import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class SelectionMenu {
	private PopupMenu slotSelectionMenu;

	public SelectionMenu(int loadoutNumber) {
		this.slotSelectionMenu = PopupMenuAPI.createMenu("Which Item?", 1);
//		slotSelectionMenu.setMenuCloseBehaviour(LoadoutMenuCloseBehaviour.getInstance());
		this.slotSelectionMenu.addMenuItem(new SelectionItem("Primary Weapon", new MaterialData(Material.DIAMOND_SWORD), Game.LoadoutSlot.Primary, loadoutNumber),
				0);
		this.slotSelectionMenu.addMenuItem(new SelectionItem("Secondary Weapon", new MaterialData(Material.ARROW), Game.LoadoutSlot.Secondary, loadoutNumber), 1);
		this.slotSelectionMenu.addMenuItem(new SelectionItem("Active Perk", new MaterialData(Material.EXP_BOTTLE), Game.LoadoutSlot.Tertiary, loadoutNumber), 2);
		this.slotSelectionMenu.setExitOnClickOutside(false);
	}

	public PopupMenu getMenu() {
		return this.slotSelectionMenu;
	}

}
