package com.caved_in.freeforall.runnables;

import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.config.WorldSpawns;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class TeleportPlayers implements Runnable {

	@Override
	public void run() {
		WorldSpawns worldSpawns = Game.configuration.getSpawnConfiguration().getWorldSpawns(Game.gameMap);
		for(GamePlayer gamePlayer : FakeboardHandler.getGamePlayers()) {
			Players.teleport(gamePlayer.getPlayer(), worldSpawns.getRandomSpawn());
		}
	}

}
