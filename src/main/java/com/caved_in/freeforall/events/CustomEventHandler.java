package com.caved_in.freeforall.events;

import com.caved_in.commons.player.PlayerWrapper;
import com.caved_in.commons.player.Players;
import com.caved_in.freeforall.Game;
import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import com.caved_in.freeforall.gamehandler.GameSetupHandler;
import com.caved_in.freeforall.gamehandler.KillstreakHandler;
import com.caved_in.freeforall.guns.GunWrapper;
import com.caved_in.freeforall.perks.Perk;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class CustomEventHandler {

	private static void callEvent(Event event) {
		Bukkit.getServer().getPluginManager().callEvent(event);
	}

	public static void handleGamePlayerDeathEvent(GamePlayerDeathEvent event) {
		callEvent(event);

		Player player = event.getEntity();
		GamePlayer gamePlayerKilled = event.getGamePlayer();
		//If the inventory is to be kept, then set the restoration inventory
		if (event.isInventoryKept()) {
			gamePlayerKilled.setDeathInventory(player.getInventory().getContents());
		}
		//Get the player who killed this player
		Player playerKiller = player.getKiller();
		if (playerKiller != null) {
			GamePlayer killingPlayer = FakeboardHandler.getPlayer(playerKiller);
			if (killingPlayer != null && gamePlayerKilled != null) {
				//Add a death to the player who was killed
				gamePlayerKilled.addDeath();
				//Add +1 score to the player who killed
				killingPlayer.addScore(1);
				//Add killstreak progress to the player who killed
				killingPlayer.addKillstreak();
				KillstreakHandler.HandleKillStreak(killingPlayer);
				gamePlayerKilled.resetKillstreak();
			}
		}
		Players.clearInventory(player);
		player.setScoreboard(gamePlayerKilled.getPlayerScoreboard().getScoreboard());
	}

	public static void handleGamePlayerRespawnEvent(GamePlayerRespawnEvent event) {
		callEvent(event);

	}

	public static void handleGameEndEvent(GameEndEvent event) {
		callEvent(event);

	}

	public static void handleGunPurchaseEvent(GunPurchaseEvent event) {
		//Call the event
		callEvent(event);
		//Assure it's not cancelled
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			GamePlayer gamePlayer = event.getGamePlayer();
			GunWrapper gunData = event.getGun();
			String gunID = gunData.getGunName();
			//Get the wrapped player data from commons
			PlayerWrapper playerWrapper = Players.getData(player.getName());
			double playerBalance = playerWrapper.getCurrency();
			//Check if the player has enough XP to purchase the gun
			if (playerBalance >= gunData.getGunPrice()) {
				playerWrapper.removeCurrency(gunData.getGunPrice());
				Players.updateData(playerWrapper);
				gamePlayer.unlockGun(gunID);
				Players.sendMessage(player, String.format("&bYou've unlocked the &e%s&b! You have &a%s&b Tunnels XP Remaining", gunID, (int) playerWrapper.getCurrency()));
				gamePlayer.getLoadout(event.getLoadoutNumber()).setPrimary(gunID);
				Players.sendMessage(player, String.format("&aThe &e%s&a is now your primary weapon for loadout #&e%s", gunID, event.getLoadoutNumber()));
			} else {
				Players.sendMessage(player, "&cYou don't have enough XP to unlock this.");
			}
		}
	}

	public static void handleLoadoutEditEvent(LoadoutEditEvent event) {
		callEvent(event);

	}

	public static void handleLoadoutSelectEvent(LoadoutSelectEvent event) {
		callEvent(event);
		//If the events not cancelled
		if (!event.isCancelled()) {
			Player player = event.getPlayer();
			GamePlayer gamePlayer = event.getGamePlayer();
			int selectedLoadout = event.getLoadout().getNumber();
			//Clear the players inventory
			Players.clearInventory(player, false);
			//Set the active loadout
			gamePlayer.setActiveLoadout(selectedLoadout);
			//Give them their primary and secondary weapons
			Game.crackShotAPI.giveWeapon(player, gamePlayer.getPrimaryGunID(), 1);
			Game.crackShotAPI.giveWeapon(player, gamePlayer.getSecondaryGunID(), 1);
			Perk activePerk = gamePlayer.getActivePerk();
			//If they've got an active perk and it isn't nothing
			if (activePerk != null && !activePerk.getPerkName().equalsIgnoreCase("Nothing")) {
				for (PotionEffect Effect : gamePlayer.getActivePerk().getEffects()) {
					player.addPotionEffect(Effect);
				}
			}
			GameSetupHandler.givePlayerLoadoutGem(player);
		}
	}

}
