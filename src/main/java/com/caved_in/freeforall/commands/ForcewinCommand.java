package com.caved_in.freeforall.commands;

import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import org.bukkit.command.CommandSender;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class ForcewinCommand {

	@CommandController.CommandHandler(name = "forcewin",
			description = "Forcewin the game to rotate players",
			permission = "gungame.admin",
			usage = "/forcewin [Team]"
	)
	public void onForceWinCommand(CommandSender sender, String[] args) {
		try {
			GamePlayer player = FakeboardHandler.getTopPlayer();
			player.addScore(75);
			Players.sendMessage(sender, "&eCapped highest scoring player score to advance the game to the next stage");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
