package com.caved_in.freeforall.menus.loadoutselector.weaponselection.primary;

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
public class PrimaryWeaponTypeMenu {
	private PopupMenu wTypeMenu;

	public PrimaryWeaponTypeMenu(int loadoutNumber) {
		wTypeMenu = PopupMenuAPI.createMenu("Select a Weapon Type", 1);
		wTypeMenu.addMenuItem(new PrimaryWeaponTypeMenuItem("Assault Rifles", new MaterialData(Material.GOLDEN_CARROT), GunType.ASSAULT, loadoutNumber), 0);
		wTypeMenu.addMenuItem(new PrimaryWeaponTypeMenuItem("Shotguns", new MaterialData(Material.STONE_HOE), GunType.SHOTGUN, loadoutNumber), 1);
		wTypeMenu.addMenuItem(new PrimaryWeaponTypeMenuItem("Sniper Rifles", new MaterialData(Material.GOLD_PICKAXE), GunType.SNIPER, loadoutNumber), 2);
		wTypeMenu.addMenuItem(new PrimaryWeaponTypeMenuItem("Special Weapons", new MaterialData(Material.DIAMOND_PICKAXE), GunType.SPECIAL, loadoutNumber), 3);
		wTypeMenu.setExitOnClickOutside(false);
	}

	public PopupMenu getMenu() {
		return this.wTypeMenu;
	}

}
