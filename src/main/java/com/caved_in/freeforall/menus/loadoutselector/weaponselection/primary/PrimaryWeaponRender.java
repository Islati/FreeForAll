package com.caved_in.freeforall.menus.loadoutselector.weaponselection.primary;

import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.guns.GunType;
import com.caved_in.freeforall.guns.GunWrapper;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class PrimaryWeaponRender {

	public static List<PrimarySelectionItem> getPrimaryWeapons(GunType gunType, int loadoutNumber, Player player) {
		List<PrimarySelectionItem> primaryWeapons = new ArrayList<>();
		GamePlayer gamePlayer = FakeboardHandler.getPlayer(player);
		for (GunWrapper gunWrapper : Game.gunHandler.getGuns(gunType)) {
			primaryWeapons.add(new PrimarySelectionItem(gunWrapper, gunWrapper.getItemStack(), loadoutNumber, gamePlayer.hasGun(gunWrapper)));
		}
		return primaryWeapons;
	}

}
