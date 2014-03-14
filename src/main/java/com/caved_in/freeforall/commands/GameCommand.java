package com.caved_in.freeforall.commands;

import com.caved_in.commons.commands.CommandController;
import com.caved_in.commons.menu.HelpScreen;
import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.Game;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GameCommand {

	@CommandController.CommandHandler(name = "game", description = "The parent command of (almost) all admin-related commands",
			permission = "game.admin", usage = "/game")
	public void onGameCommand(CommandSender Sender, String[] Args) {
		Sender.sendMessage(ChatColor.YELLOW + "For help with the /game command do '/game help'");
	}

	@CommandController.SubCommandHandler(parent = "game", name = "help", permission = "game.admin")
	public void onGameHelpCommand(CommandSender sender, String[] args) {
		HelpScreen HelpScreen = new HelpScreen("Game Admin Menu");
		HelpScreen.setHeader(ChatColor.BLUE + "<name> Page <page> of <maxpage>");
		HelpScreen.setFormat("<name> --> <desc>");
		HelpScreen.setFlipColor(ChatColor.GREEN, ChatColor.DARK_GREEN);

		HelpScreen.setEntry("/game help", "Shows this menu of commands");
		HelpScreen.setEntry("/game reload", "Reloads all the config (Shop-data, guns, and messages)");
		HelpScreen.setEntry("/setteamspean [T/CT]", "Adds a spawnpoint for the specified team");
		HelpScreen.setEntry("/forcewin [T/CT]", "Force a team to win so the round will re-start");
		HelpScreen.setEntry("/forcemap <Map>", "Forces a map change to the given map");
		HelpScreen.setEntry("/forcemap list", "List all available maps");
		int page = 1;
		if (args.length > 1 && StringUtils.isNumeric(args[1])) {
			page = Integer.parseInt(args[1]);
		}
		HelpScreen.sendTo(sender, page, 6);
	}

	@CommandController.SubCommandHandler(parent = "game", name = "reload", permission = "game.admin")
	public void ongameReloadCommand(CommandSender sender, String[] args) {
		Game.gunHandler.initData();
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "shot config reload");
		PlayerHandler.sendMessage(sender, "&a[Tunnels] GunData and ShopData reloaded");
	}
}
