package com.caved_in.freeforall.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.config.WorldSpawns;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class SpawnsCommand {

	@CommandController.CommandHandler(name = "spawns", permission = "gungame.spawn")
	public void onSpawnCommand(Player player, String[] args) {
		if (args.length > 0) {
			WorldSpawns worldSpawns = Game.getSpawns(player);
			int spawnCount = worldSpawns.getSpawnCount();
			String worldName = Players.getWorldName(player);
			Players.sendMessage(player, String.format("&e%s&a spawns in world &7%s", spawnCount, worldName));
		} else {
			Players.sendMessage(player, Messages.invalidCommandUsage("Team"));
		}
	}
}
