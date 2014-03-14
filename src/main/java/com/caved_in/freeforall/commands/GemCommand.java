package com.caved_in.freeforall.commands;

import com.caved_in.commons.commands.CommandController;
import com.caved_in.freeforall.gamehandler.GameSetupHandler;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GemCommand {

	@CommandController.CommandHandler(name = "gem", description = "Used to give yourself a loadout gem")
	public void onGemCommand(Player player, String[] args) {
		GameSetupHandler.givePlayerLoadoutGem(player);
	}
}
