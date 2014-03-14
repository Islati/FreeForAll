package com.caved_in.freeforall.menus.loadoutselector.weaponselection.tertiary;

import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.perks.Perk;
import com.caved_in.freeforall.perks.PerkHandler;
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
public class PerkRender {
	public static List<PerkMenuItem> renderPerks(int selectedLoadout, Player player) {
		List<PerkMenuItem> perkMenuItems = new ArrayList<>();
		GamePlayer gamePlayer = FakeboardHandler.getPlayer(player);
		for (Perk perk : PerkHandler.getPerks()) {
			if (!perk.getPerkName().equalsIgnoreCase("Nothing")) {
				perkMenuItems.add(new PerkMenuItem(perk, selectedLoadout, gamePlayer.hasPerk(perk)));
			}
		}
		return perkMenuItems;
	}
}
