package com.caved_in.freeforall.menus.loadoutselector.weaponselection.secondary;

import com.caved_in.freeforall.guns.GunType;
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
public class SecondaryWeaponTypeMenuItem extends MenuItem {

	private com.caved_in.freeforall.guns.GunType GunType;
	private int LoadoutNumber = 0;

	public SecondaryWeaponTypeMenuItem(String text, MaterialData icon, GunType Type, int LoadoutNumber) {
		super(text, icon);
		this.GunType = Type;
		this.LoadoutNumber = LoadoutNumber;
	}

	@Override
	public void onClick(Player Player) {
		this.getMenu().switchMenu(Player, SecondarySelectionMenu.getMenu(SecondaryWeaponRender.getSecondaryWeapons(this.GunType, this.LoadoutNumber, Player)));
	}
}
