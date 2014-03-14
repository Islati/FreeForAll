package com.caved_in.freeforall.runnables;

import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.assists.AssistManager;
import org.bukkit.Bukkit;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class AssistAggregator implements Runnable {
	private String killedName = "";
	private String killerName = "";

	public AssistAggregator(String killedName, String killerName) {
		this.killedName = killedName;
		this.killerName = killerName;
	}


	@Override
	//TODO Crete a better implementation to aggregate this data
	public void run() {
		if (AssistManager.hasData(killedName)) {
			for (String playersWhoAssisted : AssistManager.getData(killedName).getAttackers()) {
				if (!playersWhoAssisted.equalsIgnoreCase(killerName)) {
					if (PlayerHandler.isOnline(playersWhoAssisted)) {
						Game.givePlayerTunnelsXP(playersWhoAssisted, 1, true);
					}
				}
			}
			AssistManager.removeData(killedName);
			Game.givePlayerTunnelsXP(Bukkit.getPlayer(killerName), 4);
		}
	}
}
