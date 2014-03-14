package com.caved_in.freeforall.guns;

import com.caved_in.freeforall.Game;
import org.bukkit.inventory.ItemStack;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GunWrapper {
	private String gunName = "";
	private boolean isDefaultGun = false;
	private int gunPrice = 0;
	private GunType gunType;

	public GunWrapper(String gunName, int gunPrice, boolean isDefault, GunType gunType) {
		this.gunPrice = gunPrice;
		this.gunName = gunName;
		this.isDefaultGun = isDefault;
		this.gunType = gunType;
	}

	public boolean isDefaultGun() {
		return this.isDefaultGun;
	}

	public String getGunName() {
		return this.gunName;
	}

	public int getGunPrice() {
		return this.gunPrice;
	}

	public GunType getGunType() {
		return this.gunType;
	}

	public ItemStack getItemStack() {
		return Game.crackShotAPI.generateWeapon(gunName);
	}

}
