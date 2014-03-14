package com.caved_in.freeforall.runnables;

import com.caved_in.commons.Commons;
import com.caved_in.freeforall.Game;
import com.caved_in.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class ValidateMap implements Runnable {

	@Override
	public void run() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			String gameMap = Game.gameMap;
			//If there's a null map, or the world isn't loaded/exists, then try to get another
			if (gameMap == null || Bukkit.getWorld(gameMap) == null) {
				Game.gameMap = Game.getGameWorld();
			}
			try {
				//If the player's not in the world for the current game, then teleport them there
				if (!player.getWorld().getName().equalsIgnoreCase(Bukkit.getWorld(Game.gameMap).getName())) {
					if (player.getGameMode() != GameMode.CREATIVE) {
						player.teleport(WorldManager.getWorldSpawn(Game.gameMap));
					}
				}
			} catch (Exception ex) {
				Commons.messageConsole(String.format("Error in ValidateMap.java; Unable to load/get world '%s'", Game.gameMap));
			}
		}
	}

}
