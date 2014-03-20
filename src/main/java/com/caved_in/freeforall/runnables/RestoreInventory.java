package com.caved_in.freeforall.runnables;

import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.gamehandler.GameSetupHandler;
import com.caved_in.freeforall.perks.Perk;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class RestoreInventory implements Runnable {
	private ItemStack[] inventory = null;
	private String playerName = "";

	public RestoreInventory(String playerName) {
		this.playerName = playerName;
		this.inventory = FakeboardHandler.getPlayer(playerName).getDeathInventory();
	}

	@Override
	public void run() {
		Player rPlayer = Bukkit.getPlayer(playerName);
		if (rPlayer != null) {
			GamePlayer gamePlayer = FakeboardHandler.getPlayer(rPlayer);
			Players.setArmor(rPlayer, GameSetupHandler.getArmor());

			Perk playerPerk = gamePlayer.getActivePerk();
			if (playerPerk != null) {
				if (!playerPerk.getPerkName().equalsIgnoreCase("Nothing")) {
					for (PotionEffect Effect : playerPerk.getEffects()) {
						rPlayer.addPotionEffect(Effect);
					}
				}
			}
			rPlayer.getInventory().setContents(inventory);
			rPlayer.updateInventory();
		}
	}

}
