package com.caved_in.freeforall.menus.loadoutselector.weaponselection.secondary;

import com.caved_in.freeforall.guns.GunType;
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
public class SecondaryWeaponTypeMenu {
	private PopupMenu wTypeMenu;

	public SecondaryWeaponTypeMenu(int Loadout) {
		this.wTypeMenu = PopupMenuAPI.createMenu("Select a Weapon Type", 1);
		this.wTypeMenu.addMenuItem(new SecondaryWeaponTypeMenuItem("Pistol", new MaterialData(Material.GOLDEN_CARROT), GunType.PISTOL, Loadout), 0);
		this.wTypeMenu.setExitOnClickOutside(false);
	}

	public PopupMenu getMenu() {
		return this.wTypeMenu;
	}

}
