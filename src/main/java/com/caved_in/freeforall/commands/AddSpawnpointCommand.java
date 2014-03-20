package com.caved_in.freeforall.commands;

import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.location.Locations;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class AddSpawnpointCommand {

	@CommandController.CommandHandler(name = "addspawn", description = "Used to add spawn locations for teams", permission = "gungame.admin",
			usage = "/addspawn")
	public void onSetTeamSpawnCommand(Player player, String[] args) {
		Location playerLocation = player.getLocation();
		Game.configuration.getSpawnConfiguration().addSpawn(playerLocation);
		int[] cords = Locations.getXYZ(playerLocation);
		Players.sendMessage(player, String.format("&aSpawn point for world &7'%s'&a has been added to [&e%sx&a, &e%sy&a, &e%sz&a]",Players.getWorldName(player),cords[0],cords[1],cords[2]));
	}

}
