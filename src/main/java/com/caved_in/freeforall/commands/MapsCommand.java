package com.caved_in.freeforall.commands;

import com.caved_in.commons.Messages;
import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.commands.HelpMenus;
import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class MapsCommand {
	private static HelpScreen mapsMenu = null;

	private static HelpScreen getMapsMenu() {
		if (mapsMenu == null) {
			HelpScreen helpScreen = HelpMenus.generateHelpScreen("Maps List", HelpMenus.PageDisplay.DEFAULT, HelpMenus.ItemFormat.NO_DESCRIPTION, ChatColor.GREEN, ChatColor.DARK_GREEN);
			for (String worldName : Game.worldList.getContentsAsList()) {
				helpScreen.setEntry(worldName, "");
			}
			mapsMenu = helpScreen;
		}
		return mapsMenu;
	}

	@CommandController.CommandHandler(name = "maps", description = "View a list of all the available maps", usage = "/maps")
	public void onMapsCommand(Player player, String[] args) {
		int page = 1;
		if (args.length > 0) {
			String pageArg = args[0];
			if (StringUtils.isNumeric(pageArg)) {
				page = Integer.parseInt(pageArg);
			} else {
				Players.sendMessage(player, Messages.INVALID_COMMAND_USAGE("page number"));
			}
		}
		getMapsMenu().sendTo(player, page, 6);
	}
}
