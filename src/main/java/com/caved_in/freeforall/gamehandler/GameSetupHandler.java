package com.caved_in.freeforall.gamehandler;

//Commons imports

import com.caved_in.commons.Commons;
import com.caved_in.commons.items.ItemHandler;
import com.caved_in.commons.player.PlayerHandler;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.TeamType;
import com.caved_in.freeforall.config.WorldSpawns;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.fakeboard.Team;
import com.caved_in.freeforall.menus.loadoutselector.LoadoutActionMenu;
import com.caved_in.freeforall.menus.loadoutselector.LoadoutCreationMenu;
import com.caved_in.freeforall.menus.loadoutselector.LoadoutSelectionMenu;
import com.caved_in.freeforall.runnables.PlayerOpenKits;
import com.caved_in.freeforall.runnables.TeleportTeams;
import com.caved_in.worldmanager.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.kitteh.tag.TagAPI;

import java.util.Random;

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
	private static Runnable teleportTeams = new TeleportTeams();
	private static Runnable playerOpenKits = new PlayerOpenKits();

	//Blue and Red team armors
	private static ItemStack[] blueTeamArmor, redTeamArmor;

	static {
		blueTeamArmor = new ItemStack[]{
				ItemHandler.makeLeatherItemStack(Material.LEATHER_HELMET, Color.BLUE),
				ItemHandler.makeLeatherItemStack(Material.LEATHER_CHESTPLATE, Color.BLUE),
				ItemHandler.makeLeatherItemStack(Material.LEATHER_LEGGINGS, Color.BLUE),
				ItemHandler.makeLeatherItemStack(Material.LEATHER_BOOTS, Color.BLUE),
		};
		redTeamArmor = new ItemStack[]{
				ItemHandler.makeLeatherItemStack(Material.LEATHER_HELMET, Color.RED),
				ItemHandler.makeLeatherItemStack(Material.LEATHER_CHESTPLATE, Color.RED),
				ItemHandler.makeLeatherItemStack(Material.LEATHER_LEGGINGS, Color.RED),
				ItemHandler.makeLeatherItemStack(Material.LEATHER_BOOTS, Color.RED),
		};

	}

	public static ItemStack[] getTeamArmor(TeamType teamType) {
		switch (teamType) {
			case TERRORIST:
				return blueTeamArmor;
			case COUNTER_TERRORIST:
				return redTeamArmor;
			default:
				return new ItemStack[]{};
		}
	}

	public static void doSetup() {
		//Set a game in progress
		setGameInProgress(true);
		//Make all the teams
		makeGameTeams();
		//Teleport terrorists and Counter Terrorists
		Game.runnableManager.runTaskNow(teleportTeams);
		//Make all the players open their "kits"
		Game.runnableManager.runTaskLater(playerOpenKits,10L);
		//If we want to reset the last map
		if (isResetLastMap()) {
			//Get the maps name
			String mapToReset = getMapToReset();
			//Call our worldhandler to reset it
			WorldManager.rollbackMap(mapToReset);
			Commons.messageConsole("Rolled back the map [" + mapToReset + "]");
			setResetLastMap(false);
			setMapToReset("");
		}
	}

	public static void givePlayerLoadoutGem(Player player) {
		if (!ItemHandler.playerHasItem(player, Material.EMERALD, "Select & Edit Loadouts")) {
			player.getInventory().setItem(8, ItemHandler.makeItemStack(Material.EMERALD, ChatColor.GREEN + "Select & Edit Loadouts"));
		}
	}

	private static void makeGameTeams() {
		FakeboardHandler.registerTeam(TeamType.TERRORIST);
		FakeboardHandler.registerTeam(TeamType.COUNTER_TERRORIST);
		for (Player p : Bukkit.getOnlinePlayers()) {
			assignPlayerTeam(p);
		}
	}

	public static void assignPlayerTeam(final Player player) {
		//Our two teams, the terrorists and counter terrorists
		Team terroristTeam = FakeboardHandler.getTeam(TeamType.TERRORIST);
		Team counterTerroristTeam = FakeboardHandler.getTeam(TeamType.COUNTER_TERRORIST);
		//The size of each team
		int terroristCount = terroristTeam.getTeamSize();
		int counterTerroristCount = counterTerroristTeam.getTeamSize();
		GamePlayer gamePlayer = FakeboardHandler.getPlayer(player);
		if (terroristCount != counterTerroristCount) {
			//If there are more terrorists than counter terrorists, then assign to CT, otherwise T
			setTeam(gamePlayer, terroristCount > counterTerroristCount ? TeamType.COUNTER_TERRORIST : TeamType.TERRORIST);
		} else {
			//There are an exact amount of terrorists and counter-terrorists, so check against a random
			setTeam(gamePlayer, new Random().nextBoolean() ? TeamType.COUNTER_TERRORIST : TeamType.TERRORIST);
		}
		Game.runnableManager.runTaskOneTickLater(new Runnable() {
			@Override
			public void run() {
				TagAPI.refreshPlayer(player);
			}
		});
	}

	public static void setTeam(GamePlayer player, TeamType team) {
		FakeboardHandler.addToTeam(team, player);
		player.setTeam(team);
		player.getPlayer().getInventory().setArmorContents(getTeamArmor(team));
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

	public static void awardEndgamePoints(TeamType team, double winningCash, double losingCash) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			GamePlayer gamePlayer = FakeboardHandler.getPlayer(player);
			if (gamePlayer.getTeam() != null) {
				Game.givePlayerTunnelsXP(player, gamePlayer.getTeam() == team ? winningCash : losingCash);
			}
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
		WorldSpawns worldSpawns = Game.configuration.getSpawnConfiguration().getWorldSpawns(PlayerHandler.getWorldName(player));
		PlayerHandler.teleport(player, worldSpawns.getRandomSpawn(FakeboardHandler.getPlayerTeam(player)).getLocation());
	}

	public static void teleportToRandomSpawn(Player player, TeamType teamType) {
		PlayerHandler.teleport(player,Game.configuration.getSpawnConfiguration().getWorldSpawns(Game.gameMap).getRandomSpawn(teamType).getLocation());
	}
}
