package com.caved_in.freeforall.commands;

import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.menus.help.HelpMenus;
import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class ForcemapCommand {
	@CommandController.CommandHandler(name = "forcemap",
			description = "Force change a map",
			permission = "gungame.admin",
			usage = "/forcemap <Map>"
	)
	public void onForceMapCommand(CommandSender sender, String[] args) {
		//Check if we've got any arguments included
		if (args.length > 0) {
			//Get the name of the map they included as an argument
			String mapSelection = args[0];
			//Check if the argument isn't a map name, and rather list
			if (mapSelection.equalsIgnoreCase("list")) {
				//Get the help menu which lists all available maps
				HelpScreen forcemapHelp = HelpMenus.getAdminCommandsHelp();
				//Check if they included a page number aswell!
				if (args.length == 1) {
					//They didn't include a page number, so send them the first page
					forcemapHelp.sendTo(sender, 1, 7);
				} else {
					//They included a second argument, so get it
					String pageGet = args[1];
					//Check if the second argument is a number
					if (StringUtils.isNumeric(pageGet)) {
						//Its a number! Send them to the page they want
						forcemapHelp.sendTo(sender, Integer.parseInt(pageGet), 7);
					}
				}
				//They didn't want a list of the maps
			} else {
				//Now we get the list of all available maps
				List<String> worldList = Game.worldList.getContentsAsList();
				//Check if the world list contains the map they requested
				if (worldList.contains(mapSelection)) {
					//Send the player a message saying we forced a map change
					PlayerHandler.sendMessage(sender, String.format("&aMap forced to &7%s", mapSelection));
					//Actually change the map
					Game.gameMap = mapSelection;
				} else {
					//The map they requested doesn't exist; Send them the command to see all the maps
					PlayerHandler.sendMessage(sender, "&eDo &a/forcemap list&e to see a list of available maps");
				}
			}
		}
	}
}
