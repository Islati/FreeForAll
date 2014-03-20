package com.caved_in.freeforall.gamehandler;

//Commons imports

import com.caved_in.commons.item.Items;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.menus.loadoutselector.LoadoutActionMenu;
import com.caved_in.freeforall.menus.loadoutselector.LoadoutCreationMenu;
import com.caved_in.freeforall.menus.loadoutselector.LoadoutSelectionMenu;
import com.caved_in.freeforall.runnables.PlayerOpenKits;
import com.caved_in.freeforall.runnables.TeleportPlayers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.kitteh.tag.TagAPI;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GameSetupHandler {
	//If a game is currently in progress
	private static boolean gameInProgress = false;
	//Whether or not to reset the last map
	private static boolean resetLastMap = false;
	//What map needs to be reset, if any
	private static String mapToReset = "";

	private static boolean isForceMap = false;

	//Threads to handle game setup
	private static Runnable teleportPlayers = new TeleportPlayers();
	private static Runnable playerOpenKits = new PlayerOpenKits();

	//Blue and Red team armors
	private static ItemStack[] armor;

	static {
		armor = new ItemStack[]{
				Items.makeLeatherItem(Material.LEATHER_HELMET, Color.GRAY),
				Items.makeLeatherItem(Material.LEATHER_CHESTPLATE, Color.GRAY),
				Items.makeLeatherItem(Material.LEATHER_LEGGINGS, Color.GRAY),
				Items.makeLeatherItem(Material.LEATHER_BOOTS, Color.GRAY),
		};

	}

	public static ItemStack[] getArmor() {
		return armor;
	}

	public static void doSetup() {
		//Set a game in progress
		setGameInProgress(true);
		//Refresh all the players tags
		prepPlayers();
		//Teleport terrorists and Counter Terrorists
		Game.runnableManager.runTaskNow(teleportPlayers);
		//Make all the players open their "kits"
		Game.runnableManager.runTaskLater(playerOpenKits,10L);
	}

	public static void givePlayerLoadoutGem(Player player) {
		if (!Players.hasItem(player, Material.EMERALD, "Select & Edit Loadouts")) {
			player.getInventory().setItem(8, Items.makeItem(Material.EMERALD, ChatColor.GREEN + "Select & Edit Loadouts"));
		}
	}

	public static void prepPlayers() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			prepPlayer(player);
		}
	}

	public static void prepPlayer(Player player) {
		Players.setArmor(player, armor);
		refreshPlayer(player);
	}

	public static void refreshPlayer(final Player player) {
		Game.runnableManager.runTaskOneTickLater(new Runnable() {
			@Override
			public void run() {
				TagAPI.refreshPlayer(player);
			}
		});
	}

	public static boolean isGameInProgress() {
		return gameInProgress;
	}

	public static void setGameInProgress(boolean gameInProgress) {
		GameSetupHandler.gameInProgress = gameInProgress;
	}

	public static boolean isResetLastMap() {
		return resetLastMap;
	}

	public static void setResetLastMap(boolean resetLastMap) {
		GameSetupHandler.resetLastMap = resetLastMap;
	}

	public static String getMapToReset() {
		return mapToReset;
	}

	public static void setMapToReset(String mapToReset) {
		GameSetupHandler.mapToReset = mapToReset;
	}

	public static boolean isForceMap() {
		return isForceMap;
	}

	public static void setForceMap(boolean isForceMap) {
		GameSetupHandler.isForceMap = isForceMap;
	}

	public static boolean canSetup() {
		return Bukkit.getOnlinePlayers().length >= 2;
	}

	public static void awardEndgamePoints(String winningPlayerName, double winningCash, double losingCash) {
		//Check if the winning player is online, and if so award them the winning cash
		if (Players.isOnline(winningPlayerName)) {
			Game.givePlayerTunnelsXP(Players.getPlayer(winningPlayerName), winningCash);
		}

		//Loop through all the players, except the winning player, and give them the amount of cash for losing
		for(Player player : Players.allPlayersExcept(winningPlayerName)) {
			Game.givePlayerTunnelsXP(player, losingCash);
		}
	}

	public static void openCreationMenu(final Player player, boolean isAfk) {
		new LoadoutCreationMenu(player);
		FakeboardHandler.getPlayer(player).setAfk(isAfk, false);
	}

	public static void openLoadoutSelectionMenu(final Player player, boolean isAfk) {
		try {
			new LoadoutSelectionMenu(player);
			FakeboardHandler.getPlayer(player).setAfk(isAfk, false);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void openLoadoutOptionMenu(final Player player, boolean isAfk) {
		new LoadoutActionMenu(player);
		FakeboardHandler.getPlayer(player).setAfk(isAfk,false);
	}

	public static void openLoadoutOptionMenu(Player player) {
		openLoadoutOptionMenu(player, true);
	}

	public static void teleportToRandomSpawn(Player player) {
		Players.teleport(player, Game.getRandomSpawn(player));
	}
}
