package com.caved_in.freeforall.commands;

import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.gamehandler.GameSetupHandler;
import org.bukkit.entity.Player;

import static com.caved_in.freeforall.GameMessages.AFK_COMMAND_ON_COOLDOWN;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class AfkCommand {
	@CommandController.CommandHandler(name = "afk", description = "Set yourself as 'afk' which stops all damage, and turns you invisible", usage = "/afk")
	public void onAfkCommand(Player player, String[] args) {
		if (GameSetupHandler.isGameInProgress()) {
			String playerName = player.getName();
			if (!Game.afkCooldown.isOnCooldown(playerName)) {
				GamePlayer GamePlayer = FakeboardHandler.getPlayer(playerName);
				if (GamePlayer != null) {
					GamePlayer.setAfk(!GamePlayer.isAfk());
					Game.afkCooldown.setOnCooldown(playerName);
				}
			} else {
				Players.sendMessage(player, AFK_COMMAND_ON_COOLDOWN);
			}
		}
	}
}
