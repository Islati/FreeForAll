package com.caved_in.freeforall.menus.loadoutselector.weaponselection.primary;

import com.caved_in.commons.menu.Menus;
import me.xhawk87.PopupMenuAPI.PopupMenu;
import me.xhawk87.PopupMenuAPI.PopupMenuAPI;

import java.util.List;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class PrimarySelectionMenu {

	public static PopupMenu getMenu(List<PrimarySelectionItem> primarySelectionItems) {
		PopupMenu psMenu = PopupMenuAPI.createMenu("Select a Weapon", Menus.getRows(primarySelectionItems.size()));
		int i = 0;
		for(PrimarySelectionItem primarySelectionItem : primarySelectionItems) {
			if (primarySelectionItem.getIcon() != null) {
				psMenu.addMenuItem(primarySelectionItem, i);
				i += 1;
			}
		}
		psMenu.setExitOnClickOutside(false);
		return psMenu;
	}
}
