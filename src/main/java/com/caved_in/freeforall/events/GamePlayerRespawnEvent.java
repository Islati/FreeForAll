package com.caved_in.freeforall.events;

import com.caved_in.freeforall.fakeboard.FakeboardHandler;
import com.caved_in.freeforall.fakeboard.GamePlayer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE" (Revision 42):
 * <brandon@caved.in> wrote this file. As long as you retain this notice you
 * can do whatever you want with this stuff. If we meet some day, and you think
 * this stuff is worth it, you can buy me a beer in return Brandon Curtis.
 * ----------------------------------------------------------------------------
 */
public class GamePlayerRespawnEvent extends PlayerRespawnEvent {

	public static final HandlerList handlers = new HandlerList();

	private GamePlayer gamePlayer;

	public GamePlayerRespawnEvent(Player respawnPlayer, Location respawnLocation, boolean isBedSpawn) {
		super(respawnPlayer, respawnLocation, isBedSpawn);
		this.gamePlayer = FakeboardHandler.getPlayer(player);
	}

	public GamePlayer getGamePlayer() {
		return gamePlayer;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
